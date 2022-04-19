package net.coagulate.JSLBot.Packets;

import net.coagulate.JSLBot.Packets.Types.Type;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.*;

/** Generic Block - the pieces that build UDP messages.
 *
 * @author Iain Price
 */
public abstract class Block {
    @Nonnull
    public ByteBuffer writeBlock() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void readBlock(final ByteBuffer in) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @SuppressWarnings("unchecked") // reflect is a pain enough without force parameterising everything
    public int fieldSize(@Nonnull final Field f) {
        if (Block.class.isAssignableFrom(f.getType())) {
            try {
                // its a block
                @Nonnull final Block b = (Block) (f.getType().getDeclaredConstructor().newInstance());
                return b.size();
            } catch (@Nonnull final InvocationTargetException | NoSuchMethodException | InstantiationException |
                                    IllegalAccessException ex) {
                throw new IllegalArgumentException("Unable to size supposed Block " + this.getClass().getName() + "/" + f.getName() + " type " + f.getType().getName(), ex);
            }
        }
        if (List.class.isAssignableFrom(f.getType())) {
            try {
                @Nonnull final List<Block> l = (List<Block>) (f.get(this));
                @Nonnull final Class<?> listtype = (Class) ((ParameterizedType) (f.getGenericType())).getActualTypeArguments()[0];
                int size = 0;
                for (@Nonnull final Block b : l) {
                    size = size + b.size();
                }
                return size + (new U8().size());
            } catch (@Nonnull final IllegalAccessException | IllegalArgumentException e) {
                throw new IllegalArgumentException("Unable to size supposed variable count block " + this.getClass().getName() + "/" + f.getName() + " type " + f.getType().getName(), e);
            }
        }
        try {
            @Nonnull final Type lltype = (Type) f.get(this);
            return lltype.size();
        } catch (final IllegalAccessException ex) {
            throw new IllegalArgumentException("Unable to size supposed LL Type " + this.getClass().getName() + " field " + f.getName() + " of type " + f.getType().getName(), ex);
        } catch (final NullPointerException e) {
            throw new IllegalArgumentException("Block assembly failure " + f.getName() + " is null", e);
        }
    }
    // gets the block's data fields, in order
    @Nonnull
    private List<Field> getFields() {
        // NOTE the fields all seem to come back in the order they're declared (Oracle JDK 8)
        // this isn't actually guaranteed by the method however, so we're annotating the fields with an ordering number and going off that
        // must start at 0 and be sequential.  The sub blocks start at zero themselves too.
        @Nonnull final Map<Integer, Field> map = new HashMap<>();
        @Nonnull final Field[] fs = this.getClass().getDeclaredFields();
        for (@Nonnull final Field f : fs) {
            if (!"this$0".equals(f.getName())) {
                final Sequence a = f.getDeclaredAnnotation(Sequence.class);
                // all fields in the block MUST have a sequence
                if (a == null) {
                    throw new IllegalArgumentException("Field " + f.getName() + " of type " + f.getType().getName() + " does not have annotation Sequence (?)");
                }
                map.put(a.value(), f);
            }
        }
        @Nonnull final List<Field> result = new ArrayList<>();
        @Nonnull final List<Integer> sequence = new ArrayList<>(map.keySet());
        Collections.sort(sequence);
        for (final Integer i : sequence) {
            result.add(map.get(i));
        }
        return result;
    }
    public int size() {
        final boolean debug = false;
        if (debug) {
            System.out.println("Sizing " + this.getClass().getName());
        }
        int size = 0;
        @Nonnull final List<Field> fields = getFields();
        for (@Nonnull final Field f : fields) {
            if (Block.class.isAssignableFrom(f.getType())) {
                if (debug) {
                    System.out.println("> Enter Block recursion: " + f.getType().getName());
                }
                try {
                    @Nonnull final Block b = (Block) (f.get(this));
                    if (b == null) {
                        throw new NullPointerException("Block contents " + this.getClass().getSimpleName() + " is null");
                    }
                    size += b.size();
                } catch (@Nonnull final IllegalArgumentException | IllegalAccessException ex) {
                    throw new IllegalArgumentException("Exception in block " + this.getClass().getSimpleName(), ex);
                }
                if (debug) {
                    System.out.println("< Exit Block recursion: " + f.getType().getName());
                }
            } else {
                if (debug) {
                    System.out.println("Field " + f.getName() + " of type " + f.getType().getName());
                }
                // cache me?, cos this is stupidly inefficient compared.  though probably still meaningless?
                // removed this as a 'task entry', this may not be this simple, there are Variable length fields, perhaps we have to just scan.
                size += fieldSize(f);
            }

        }
        return size;
    }

    public void messageRead(final ByteBuffer in) {
        throw new UnsupportedOperationException("NOTIMP");
    }

    private void fieldToBytes(@Nonnull final Field f, @Nonnull final ByteBuffer output) {
        final boolean debug = false;
        if (debug) {
            System.out.println("Outputting field " + f.getName() + " of type " + f.getType().getName());
        }
        if (Type.class.isAssignableFrom(f.getType())) {
            try {
                @Nonnull final Type t = (Type) (f.get(this));
                t.write(output);
                return;
            } catch (@Nonnull final IllegalArgumentException | IllegalAccessException ex) {
                throw new IllegalArgumentException("Internal error during field to bytes on field " + f.getName() + " of type " + f.getType().getName(), ex);
            }
        }
        throw new UnsupportedOperationException("Unknown field type, field name " + f.getName() + " type " + f.getType().getName());
    }

    private void blockToBytes(final Block b, @Nonnull final ByteBuffer out) {
        for (@Nonnull final Field f : getFields()) {
            fieldToBytes(f, out);
        }
    }

    public void writeBytes(@Nonnull final ByteBuffer out) {
        @Nonnull final List<Field> fields = getFields();
        for (@Nonnull final Field f : fields) {
            writeField(out, f);
        }
    }

    private void writeField(@Nonnull final ByteBuffer out, @Nonnull final Field f) {
        if (Block.class.isAssignableFrom(f.getType())) {
            final Block b;
            try {
                b = (Block) f.get(this);
                b.writeBytes(out);
                return;
            } catch (@Nonnull final IllegalAccessException | IllegalArgumentException ex) {
                throw new IllegalArgumentException("Error writing field " + f.getName() + " of type " + f.getType().getName(), ex);
            }
        }
        if (List.class.isAssignableFrom(f.getType())) {
            try {
                @Nonnull @SuppressWarnings("unchecked") final List<Block> l = (List<Block>) (f.get(this));
                @Nonnull final U8 qty = new U8();
                qty.value = (byte) l.size();
                qty.write(out);
                for (@Nonnull final Block b : l) {
                    b.writeBytes(out);
                }
                return;
            } catch (@Nonnull final IllegalAccessException | IllegalArgumentException e) {
                throw new IllegalArgumentException("Error writing variable count block " + this.getClass().getName() + "/" + f.getName() + " type " + f.getType().getName(), e);
            }
        }
        fieldToBytes(f, out);
    }

    public void readBytes(@Nonnull final ByteBuffer in) {
        for (@Nonnull final Field f : getFields()) {
            readField(in, f);
        }
    }

    private void readField(@Nonnull final ByteBuffer in, @Nonnull final Field f) {
        final boolean debug = false;
        if (debug) {
            System.out.println("READING FIELD " + f.getName() + " from " + this.getClass().getName() + " type " + f.getType().getName());
        }
        if (Block.class.isAssignableFrom(f.getType())) {
            final Block b;
            try {
                b = (Block) f.get(this);
                b.readBytes(in);
                return;
            } catch (@Nonnull final IllegalAccessException | IllegalArgumentException ex) {
                throw new IllegalArgumentException("Error reading Block field " + f.getName() + " of type " + f.getType().getName(), ex);
            }
        }
        if (List.class.isAssignableFrom(f.getType())) {
            try {
                @Nonnull final U8 qty = new U8(0);
                try { qty.read(in); } catch (final BufferUnderflowException ignored) {
                    //System.out.println("Exception reading qty ; default zero");
                }
                @Nonnull final List<Block> list = new ArrayList<>();
                for (int i = 0; i < qty.value; i++) {
                    @Nonnull final Class<?> listtype = (Class) ((ParameterizedType) (f.getGenericType())).getActualTypeArguments()[0];
                    if (debug) {
                        System.out.println(listtype.getName());
                    }
                    @Nonnull final Block b = (Block) listtype.getDeclaredConstructor().newInstance();
                    b.readBytes(in);
                    list.add(b);
                }
                f.set(this, list);
                return;
            } catch (@Nonnull final IllegalAccessException | IllegalArgumentException | InstantiationException |
                                    NoSuchMethodException | InvocationTargetException e) {
                throw new IllegalArgumentException("Failed to read LIST type (variable block type) from class " + this.getClass().getName() + " field " + f.getName() + " type " + f.getType().getName() + " generic type " + f.getGenericType().getTypeName(), e);
            }
        }

        // else, a LL type?
        try {
            @Nonnull final Type value = (Type) f.getType().getDeclaredConstructor().newInstance();
            try {
                value.read(in);
            } catch (final BufferUnderflowException ignored) {
                //System.out.println("Buffer underflow reading a data type "+f.getType());
            }
            f.set(this, value);
        } catch (@Nonnull final IllegalAccessException | IllegalArgumentException | InstantiationException |
                                NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalArgumentException("Failed to set class " + this.getClass().getName() + " field " + f.getName() + " type " + f.getType().getName(), e);
        }
    }

    public String dump() {
        final StringBuilder ret = new StringBuilder();
        ret.append(" [==").append(this.getClass().getSimpleName()).append("==]\n");
        for (@Nonnull final Field f : getFields()) {
            ret.append(dumpField(f));
        }
        return ret.toString();
    }

    private String dumpField(@Nonnull final Field f) {
        final StringBuilder ret = new StringBuilder();
        ret.append("   field: ").append(f.getName()).append("[").append(f.getType().getSimpleName()).append("]=");
        try {
            final Object o = f.get(this);
            if (Block.class.isAssignableFrom(o.getClass())) {
                @Nonnull final Block b = (Block) o;
                ret.append(b.dump());
                return ret.toString();
            }
            if (List.class.isAssignableFrom(o.getClass())) {
                @Nonnull @SuppressWarnings("unchecked") final List<Block> l = (List<Block>) o;
                int counter = 0;
                for (@Nonnull final Block b : l) {
                    ret.append("{ #").append(counter).append(" }  ").append(b.dump());
                    counter++;
                }
            }
            ret.append(o).append("\n");
        } catch (@Nonnull final NullPointerException | IllegalAccessException | IllegalArgumentException e) {
            ret.append(e);
        }
        return ret.toString();
    }
}
