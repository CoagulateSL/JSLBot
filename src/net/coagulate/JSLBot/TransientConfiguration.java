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
    private Map<String,String> kv=new HashMap<>();

    @Override
    public String get(String param) {
        if (kv.containsKey(param)) { return kv.get(param); }
        return null;
    }

    @Override
    public void put(String param, String value) {
        kv.put(param,value);
    }

    public String dump() {
        String response="";
        for (String k:kv.keySet()) {
            if (!response.equals("")) { response+="\n"; }
            response+=k+"="+kv.get(k); 
        }
        return response;
    }   

    @Override
    public Set<String> get() {
        return kv.keySet();
    }
}
