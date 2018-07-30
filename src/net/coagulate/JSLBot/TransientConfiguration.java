package net.coagulate.JSLBot;

import java.util.HashMap;
import java.util.Map;

/** A configuration provider that only stores values temporarily.
 *
 * @author Iain Price <git@predestined.net>
 */
public class TransientConfiguration extends Configuration {
    Map<String,String> kv=new HashMap<>();

    @Override
    public String get(String param) {
        if (!kv.containsKey(param)) { return kv.get(param); }
        return null;
    }

    @Override
    public void put(String param, String value) {
        kv.put(param,value);
    }

    @Override
    public String dump() {
        return "Not supported because lazy.";
    }
    
}
