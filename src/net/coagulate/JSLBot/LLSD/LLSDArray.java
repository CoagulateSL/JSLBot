package net.coagulate.JSLBot.LLSD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Iain
 */
public class LLSDArray extends Container implements Iterable {
    private List<Atomic> data=new ArrayList<>();
    
    public LLSDArray() {}

    public LLSDArray(NodeList nodes) throws IOException {
        for (int i=0;i<nodes.getLength();i++) {
            Node n=nodes.item(i);
            Atomic atom=Atomic.create(n);
            if (atom!=null) { data.add(atom); }
        }
    }
    
    public void add(String s) { data.add(new LLSDString(s)); }
    public void add(Atomic a) { data.add(a); }
    public List<Atomic> get() { return data; }
    
    public String toXML(String prefix) {
        String resp=prefix+"<array>\n";
        for (Atomic a:data) { resp+=a.toXML(prefix+"  "); }
        resp+=prefix+"</array>\n";
        return resp;
    }

    @Override
    public Iterator iterator() {
        return data.iterator();
    }
}
