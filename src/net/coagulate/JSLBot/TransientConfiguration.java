package net.coagulate.JSLBot;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** A configuration provider that only stores values temporarily.
 *
 * @author Iain Price
 */
public class TransientConfiguration extends Configuration {
    // our transient store
    private final Map<String,String> kv=new HashMap<>();

    @Override
    public String get(String param) {
        if (kv.containsKey(param)) { return kv.get(param); }
        return null;
    }

    @Override
    public void put(String param, String value) {
        kv.put(param,value);
    }

    @Override
    public String dump() {
        String response="";
        for (Map.Entry<String, String> entry : kv.entrySet()) {
            if (!"".equals(response)) { response+="\n"; }
            response+= entry.getKey() +"="+ entry.getValue();
        }
        return response;
    }   

    @Override
    public Set<String> get() {
        return kv.keySet();
    }

    @Override
    public boolean persistent() { return false; }
}
