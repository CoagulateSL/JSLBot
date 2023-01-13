/*  STATIC DATA
 */
package net.coagulate.JSLBot;

import net.coagulate.JSLBot.Packets.Types.LLUUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Stuff that all bots can share, immutable data of Second Life.
 *
 * @author Iain Price
 */
public final class Global {
	private static final Map<LLUUID,String> lastnames   =new HashMap<>();
	private static final Map<LLUUID,String> firstnames  =new HashMap<>();
	private static final Map<LLUUID,String> displaynames=new HashMap<>();
	private static final Map<LLUUID,String> usernames   =new HashMap<>();
	private static final Map<Long,String>   regionnames =new HashMap<>();
	
	// ---------- STATICS ----------
	
	/**
	 * Look up a region name via the cache
	 *
	 * @param handle Long region handle to look up
	 * @return The name of the region attached.
	 */
	public static String regionName(final Long handle) {
		synchronized(regionnames) {
			return regionnames.get(handle);
		}
	}
	
	/**
	 * Set a region name in the cache
	 *
	 * @param handle Long region handle to cache
	 * @param name   Region name for the handle
	 */
	public static void regionName(@Nullable final Long handle,@Nullable final String name) {
		if (handle!=null&&name!=null&&(!name.isEmpty())) {
			synchronized(regionnames) {
				regionnames.put(handle,name);
			}
		}
	}
	
	/**
	 * Look up a region handle in the cache
	 *
	 * @param name Name of the region to look up
	 * @return Long region handle
	 */
	@Nullable
	public static Long regionHandle(final String name) {
		synchronized(regionnames) {
			for (@Nonnull final Map.Entry<Long,String> entry: regionnames.entrySet()) {
				if (entry.getValue().equalsIgnoreCase(name)) {
					return entry.getKey();
				}
			}
		}
		return null;
	}
	
	// this would be much better if we factoried the UUIDs and could just use == (i.e. "xxx.get(LLUUID)" directly
	// maybe we can, this fault was never well tested.
	
	// ----- Internal Statics -----
	
	/**
	 * Cache a last name
	 */
	static void lastName(final LLUUID uuid,final String lastname) {
		synchronized(lastnames) {
			lastnames.put(uuid,lastname);
		}
	}
	
	/**
	 * Cache a first name
	 */
	static void firstName(final LLUUID uuid,final String firstname) {
		synchronized(firstnames) {
			firstnames.put(uuid,firstname);
		}
	}
	
	/**
	 * Cache a display name
	 */
	static void displayName(final LLUUID uuid,final String displayname) {
		synchronized(displaynames) {
			displaynames.put(uuid,displayname);
		}
	}
	
	/**
	 * Cache a username
	 */
	static void userName(final LLUUID uuid,final String username) {
		synchronized(usernames) {
			usernames.put(uuid,username);
		}
	}
	
	/**
	 * Look up a display name ONLY in the cache
	 */
	@Nullable
	static String displayName(final LLUUID uuid) {
		synchronized(displaynames) {
			for (@Nonnull final Map.Entry<LLUUID,String> entry: displaynames.entrySet()) {
				if (entry.getKey().equals(uuid)) {
					return entry.getValue();
				}
			}
			return null;
		}
	}
	
	/**
	 * Look up a first name ONLY in the cache
	 */
	@Nullable
	static String firstName(final LLUUID uuid) {
		synchronized(firstnames) {
			for (@Nonnull final Map.Entry<LLUUID,String> entry: firstnames.entrySet()) {
				if (entry.getKey().equals(uuid)) {
					return entry.getValue();
				}
			}
			return null;
		}
	}
	
	/**
	 * Look up a last name ONLY in the cache
	 */
	@Nullable
	static String lastName(final LLUUID uuid) {
		synchronized(lastnames) {
			for (@Nonnull final Map.Entry<LLUUID,String> entry: lastnames.entrySet()) {
				if (entry.getKey().equals(uuid)) {
					return entry.getValue();
				}
			}
			return null;
		}
	}
	
	/**
	 * Look up a user name ONLY in the cache
	 */
	@Nullable
	static String userName(final LLUUID uuid) {
		synchronized(usernames) {
			for (@Nonnull final Map.Entry<LLUUID,String> entry: usernames.entrySet()) {
				if (entry.getKey().equals(uuid)) {
					return entry.getValue();
				}
			}
			return null;
		}
	}
}
