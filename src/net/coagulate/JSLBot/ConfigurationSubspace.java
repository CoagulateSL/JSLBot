package net.coagulate.JSLBot;

import javax.annotation.Nullable;
import java.util.Set;

/** A configuration "subspace", basically just uses "." separators in the key name.
 *
 * @author Iain Price
 */
public class ConfigurationSubspace extends Configuration {

    final Configuration c;
    final String p;
    public ConfigurationSubspace(Configuration c,String prefix) {
        this.c=c;
        p=prefix;
    }
    @Nullable
    @Override
    public String get(String param) {
        return c.get(p+"."+param);
    }

    @Override
    public void put(String param, String value) {
        c.put(p+"."+param,value);
    }

    @Override
    public String dump() {
        return c.dump();
    }
    
    @Override
    public Configuration getMaster() {return c;}

    @Override
    public Set<String> get() {
        return getMaster().get();
    }

    @Override
    public boolean persistent() { return getMaster().persistent(); }
    
}
