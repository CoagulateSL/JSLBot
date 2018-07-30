/*  STATIC DATA
 */
package net.coagulate.JSLBot;

import java.util.HashMap;
import java.util.Map;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U64;

/**  Stuff that all instances can share, that has nothign to do with us.
 *
 * @author Iain Price <git@predestined.net>
 */
public final class Global {
    private static final Map<LLUUID,String> lastnames=new HashMap<>();
    private static final Map<LLUUID,String> firstnames=new HashMap<>();
    private static final Map<LLUUID,String> displaynames=new HashMap<>();
    private static final Map<LLUUID,String> usernames=new HashMap<>();
    
    public static void setLastName(LLUUID uuid,String lastname) { lastnames.put(uuid,lastname); }
    public static void setFirstName(LLUUID uuid,String firstname) { firstnames.put(uuid,firstname); }
    public static void setDisplayName(LLUUID uuid,String displayname) { displaynames.put(uuid,displayname); }
    public static void setUserName(LLUUID uuid,String username) { usernames.put(uuid,username); }

    // this would be much better if we factoried the UUIDs and could just use == (i.e. "xxx.get(LLUUID)" directly
    // maybe we can, this fault was never well tested.
    public static String getDisplayName(LLUUID uuid) { 
        for (LLUUID search:displaynames.keySet()) {
            if (search.equals(uuid)) { return displaynames.get(search); }
        }
        return null;
    }
    public static String getFirstName(LLUUID uuid) { 
        for (LLUUID search:firstnames.keySet()) {
            if (search.equals(uuid)) { return firstnames.get(search); }
        }
        return null;
    }
    public static String getLastName(LLUUID uuid) { 
        for (LLUUID search:lastnames.keySet()) {
            if (search.equals(uuid)) { return lastnames.get(search); }
        }
        return null;
    }
    public static String getUserName(LLUUID uuid) { 
        for (LLUUID search:usernames.keySet()) {
            if (search.equals(uuid)) { return usernames.get(search); }
        }
        return null;
    }
    
    private static Map<Long,String> regionnames=new HashMap<>();
    public static String getRegionName(Long handle) { return regionnames.get(handle); }
    public static void setRegionName(Long handle,String name) { if (handle!=null && name!=null && (!name.isEmpty())) { synchronized(regionnames) { regionnames.put(handle, name); } } }
    public static Long getRegionHandle(String name) {
        synchronized(regionnames) {
            for (Long handle:regionnames.keySet()) {
                if (regionnames.get(handle).equalsIgnoreCase(name)) { return handle; }
            }
        }
        return null;
    }
}
