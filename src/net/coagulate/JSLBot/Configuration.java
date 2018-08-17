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
    /** Get a configuration element, or its default value
     * 
     * @param key
     * @param defaultvalue
     * @return 
     */
    public String get(String key,String defaultvalue) { 
        String value=get(key);
        if (value==null) { return defaultvalue; }
        return value;
    }
    /** Configuration services must have a 'get' command
     * 
     * @param param
     * @return 
     */
    public abstract String get(String param);
    /** Configuration services must have a 'put' command
     * 
     * @param param
     * @param value 
     */
    public abstract void put(String param,String value);    

    /** Get all the keys.
     *
     * @return
     */
    public abstract Set<String> get();

    /** Debugging / dump all data.
     *
     * @return
     */
    public abstract String dump();
    /** For seeking the root of the configuration tree, subspaces will point this upwards
     * 
     * @return 
     */
    public Configuration getMaster() { return this; }
}
