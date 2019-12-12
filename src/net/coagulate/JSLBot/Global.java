/*  STATIC DATA
 */
package net.coagulate.JSLBot;

import java.util.HashMap;
import java.util.Map;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

/**  Stuff that all bots can share, immutable data of Second Life.
 *
 * @author Iain Price
 */
public final class Global {
    private static final Map<LLUUID,String> lastnames=new HashMap<>();
    private static final Map<LLUUID,String> firstnames=new HashMap<>();
    private static final Map<LLUUID,String> displaynames=new HashMap<>();
    private static final Map<LLUUID,String> usernames=new HashMap<>();
    
    /** Cache a last name */
    static void lastName(LLUUID uuid,String lastname) { synchronized(lastname) { lastnames.put(uuid,lastname); } }
    /** Cache a first name */
    static void firstName(LLUUID uuid,String firstname) { synchronized(firstnames) { firstnames.put(uuid,firstname); } }
    /** Cache a display name */
    static void displayName(LLUUID uuid,String displayname) { synchronized(displaynames) { displaynames.put(uuid,displayname); } }
    /** Cache a username */
    static void userName(LLUUID uuid,String username) { synchronized(usernames) { usernames.put(uuid,username); } }

    // this would be much better if we factoried the UUIDs and could just use == (i.e. "xxx.get(LLUUID)" directly
    // maybe we can, this fault was never well tested.
    /** Look up a display name ONLY in the cache */
    static String displayName(LLUUID uuid) { 
        synchronized(displaynames) {
            for (Map.Entry<LLUUID, String> entry : displaynames.entrySet()) {
                if (entry.getKey().equals(uuid)) { return entry.getValue(); }
            }
            return null;
        }
    }
    /** Look up a first name ONLY in the cache */
    static String firstName(LLUUID uuid) { 
        synchronized(firstnames) {
            for (Map.Entry<LLUUID, String> entry : firstnames.entrySet()) {
                if (entry.getKey().equals(uuid)) { return entry.getValue(); }
            }
            return null;
        }
    }
    /** Look up a last name ONLY in the cache */
    static String lastName(LLUUID uuid) { 
        synchronized(lastnames) { 
            for (Map.Entry<LLUUID, String> entry : lastnames.entrySet()) {
                if (entry.getKey().equals(uuid)) { return entry.getValue(); }
            }
            return null;
        }
    }
    /** Look up a user name ONLY in the cache */
    static String userName(LLUUID uuid) { 
        synchronized(usernames) { 
            for (Map.Entry<LLUUID, String> entry : usernames.entrySet()) {
                if (entry.getKey().equals(uuid)) { return entry.getValue(); }
            }
            return null;
        }
    }
    
    private static final Map<Long,String> regionnames=new HashMap<>();
    /** Look up a region name via the cache
     * @param handle Long region handle to look up
     * @return The name of the region attached.
     */
    public static String regionName(Long handle) { synchronized(regionnames) { return regionnames.get(handle); } } 
    /** Set a region name in the cache
     * @param handle Long region handle to cache
     * @param name Region name for the handle
     */
    public static void regionName(Long handle,String name) { if (handle!=null && name!=null && (!name.isEmpty())) { synchronized(regionnames) { regionnames.put(handle, name); } } }
    /** Look up a region handle in the cache
     * 
     * @param name Name of the region to look up
     * @return Long region handle
     */
    public static Long regionHandle(String name) {
        synchronized(regionnames) {
            for (Map.Entry<Long, String> entry : regionnames.entrySet()) {
                if (entry.getValue().equalsIgnoreCase(name)) { return entry.getKey(); }
            }
        }
        return null;
    }
}
