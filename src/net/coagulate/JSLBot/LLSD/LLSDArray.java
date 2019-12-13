package net.coagulate.JSLBot.LLSD;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Represents an Array in LLSD format
 *
 * @author Iain Price
 */
public class LLSDArray extends Container implements Iterable<Atomic> {
    private final List<Atomic> data = new ArrayList<>();
    
    public LLSDArray() {}

    public LLSDArray(@Nonnull NodeList nodes) {
        for (int i=0;i<nodes.getLength();i++) {
            Node n=nodes.item(i);
            Atomic atom=Atomic.create(n);
            if (atom!=null) { data.add(atom); }
        }
    }
    
    public void add(String s) { data.add(new LLSDString(s)); }
    public void add(Atomic a) { data.add(a); }
    @Nonnull
    public List<Atomic> get() { return data; }
    
    @Nonnull
    @Override
    public String toXML(String prefix) {
        StringBuilder resp= new StringBuilder(prefix + "<array>\n");
        for (Atomic a:data) { resp.append(a.toXML(prefix + "  ")); }
        resp.append(prefix).append("</array>\n");
        return resp.toString();
    }

    @NotNull
    @Override
    public Iterator<Atomic> iterator() {
        return data.iterator();
    }
}
