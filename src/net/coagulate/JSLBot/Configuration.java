package net.coagulate.JSLBot;

/** General abstract API for the configuration system
 *
 * @author Iain Price <git@predestined.net>
 */
public abstract class Configuration {
    
    public Configuration subspace(String prefix) {
        return new ConfigurationSubspace(this,prefix);
    }
    
    public String get(String key,String defaultvalue) { 
        String value=get(key);
        if (value==null) { return defaultvalue; }
        return value;
    }
    public abstract String get(String param);
    
    public abstract void put(String param,String value);    
    
    public abstract String dump();
    public Configuration getMaster() { return this; }
}
