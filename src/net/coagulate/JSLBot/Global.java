/*  STATIC DATA
 */
package net.coagulate.JSLBot;

import java.util.HashMap;
import java.util.Map;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

/**  Stuff that all instances can share, that has nothign to do with us.
 *
 * @author Iain Price <git@predestined.net>
 */
public final class Global {
    private static final Map<LLUUID,String> lastnames=new HashMap<>();
    private static final Map<LLUUID,String> firstnames=new HashMap<>();
    private static final Map<LLUUID,String> displaynames=new HashMap<>();
    private static final Map<LLUUID,String> usernames=new HashMap<>();
    
    /** Cache a last name */
    static void lastName(LLUUID uuid,String lastname) { lastnames.put(uuid,lastname); }
    /** Cache a first name */
    static void firstName(LLUUID uuid,String firstname) { firstnames.put(uuid,firstname); }
    /** Cache a display name */
    static void displayName(LLUUID uuid,String displayname) { displaynames.put(uuid,displayname); }
    /** Cache a username */
    static void userName(LLUUID uuid,String username) { usernames.put(uuid,username); }

    // this would be much better if we factoried the UUIDs and could just use == (i.e. "xxx.get(LLUUID)" directly
    // maybe we can, this fault was never well tested.
    /** Look up a display name ONLY in the cache */
    static String displayName(LLUUID uuid) { 
        for (LLUUID search:displaynames.keySet()) {
            if (search.equals(uuid)) { return displaynames.get(search); }
        }
        return null;
    }
    /** Look up a first name ONLY in the cache */
    static String firstName(LLUUID uuid) { 
        for (LLUUID search:firstnames.keySet()) {
            if (search.equals(uuid)) { return firstnames.get(search); }
        }
        return null;
    }
    /** Look up a last name ONLY in the cache */
    static String lastName(LLUUID uuid) { 
        for (LLUUID search:lastnames.keySet()) {
            if (search.equals(uuid)) { return lastnames.get(search); }
        }
        return null;
    }
    /** Look up a user name ONLY in the cache */
    static String userName(LLUUID uuid) { 
        for (LLUUID search:usernames.keySet()) {
            if (search.equals(uuid)) { return usernames.get(search); }
        }
        return null;
    }
    
    private static Map<Long,String> regionnames=new HashMap<>();
    /** Look up a region name via the cache */
    public static String regionName(Long handle) { return regionnames.get(handle); }
    /** Set a region name in the cache */
    public static void regionName(Long handle,String name) { if (handle!=null && name!=null && (!name.isEmpty())) { synchronized(regionnames) { regionnames.put(handle, name); } } }
    /** Look up a region handle in the cache */
    public static Long regionHandle(String name) {
        synchronized(regionnames) {
            for (Long handle:regionnames.keySet()) {
                if (regionnames.get(handle).equalsIgnoreCase(name)) { return handle; }
            }
        }
        return null;
    }
}
