package net.coagulate.JSLBot;

import java.util.Set;

/** General abstract API for the configuration system, K=V style.
 *
 * @author Iain Price
 */
public abstract class Configuration {
    
    /** Create a subspace inside this configuration.
     * By applying a common prefix to all the subspace methods, a hierarchical structure of preferences (in 'folders' if you like) can be constructed.
     * 
     * @param prefix Prefix pre-pended to all future get/put requests.
     * @return 
     */
    public Configuration subspace(String prefix) {
        return new ConfigurationSubspace(this,prefix);
    }
    /** Get a configuration element, or its default value */
    public String get(String key,String defaultvalue) { 
        String value=get(key);
        if (value==null) { return defaultvalue; }
        return value;
    }
    /** Configuration services must have a 'get' command */
    public abstract String get(String param);
    /** Configuration services must have a 'put' command */
    public abstract void put(String param,String value);    
    public abstract Set<String> get();
    public abstract String dump();
    /** For seeking the root of the configuration tree, subspaces will point this upwards */
    public Configuration getMaster() { return this; }
}
