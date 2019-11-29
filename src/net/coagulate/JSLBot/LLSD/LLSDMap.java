package net.coagulate.JSLBot.LLSD;

import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** A Map container in LLSD.
 *
 * @author Iain Price
 */
public class LLSDMap extends Container {
    private final Map<String,Atomic> data=new HashMap<>();
    
    /** Create an LLSD Map from a list of XML nodes.
     *
     * @param nodes NodeList contents of this map
     */
    public LLSDMap(NodeList nodes) {
        for (int i=0;i<nodes.getLength();i+=2) {
            String key=nodes.item(i).getFirstChild().getNodeValue();
            Atomic a=Atomic.create(nodes.item(i+1));
            if (a!=null) { data.put(key, a); }
        }
    }

    public LLSDMap() {
    }
    
    
    @Override
    public String toXML(String prefix) {
        String resp=prefix+"<map>\n";
        for (String key:data.keySet()) {
            resp+=prefix+"<key>"+key+"</key>\n";
            resp+=data.get(key).toXML(prefix+"  ");
        }
        resp+=prefix+"</map>\n";
        return resp;
    }

    public Set<String> keys() { return data.keySet(); }
    public boolean containsKey(String key) {
        return data.containsKey(key);
    }
    public Atomic get(String key) { return data.get(key); }
    
    /** Get a key, with a default value if not present.
     *
     * @param key Key to get
     * @param def Default atomic to return
     * @return The atomic from the map, or the default if not present.
     */
    public Atomic get(String key,Atomic def) { return data.getOrDefault(key, def); }

    public void put(String ack, Atomic atom) {
        data.put(ack,atom);
    }
}
