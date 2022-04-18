package net.coagulate.JSLBot.Packets.Types;

import javax.annotation.Nonnull;
import java.nio.ByteBuffer;

/**
 * @author Iain Price
 */
public class Variable2 extends Type {

    @Nonnull
    public byte[] value = new byte[0];

    public Variable2() {
    }

    public Variable2(@Nonnull final String string) {
        @Nonnull final char[] array = string.toCharArray();
        value = new byte[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            value[i] = (byte) array[i];
        }
        value[value.length - 1] = 0;
    }

    public Variable2(final LLUUID uuid) {
        value = new byte[16];
        System.arraycopy(uuid.uuid, 0, value, 0, 16);
        // simple fixed conversion, uuid is known to be
    }

    public Variable2(final byte[] binary) {
        value = new byte[binary.length];
        System.arraycopy(binary, 0, value, 0, binary.length);
    }

    @Override
    public int size() {
        return value.length+(new U16().size());
    }

    @Override
    public void read(@Nonnull final ByteBuffer in) {
        @Nonnull final U16 length = new U16();
        length.read(in);
        final int len = (length.value) & 0xffff;
        value = new byte[len];
        for (int i = 0; i < len; i++) {
            value[i] = in.get();
        }
    }

    @Override
    public void write(@Nonnull final ByteBuffer out) {
        @Nonnull final U16 length = new U16();
        length.value = (short) value.length;
        length.write(out);
        out.put(value);
    }

    @Nonnull
    @Override
    public String dump() {
        return "'"+ this +"'";
    }
    @Nonnull
    public String toString() {
        @Nonnull final StringBuilder str= new StringBuilder();
        for (final byte b : value) {
            if (b > 0) {
                str.append((char) b);
            }
        }
        return str.toString();
    }
        
}
