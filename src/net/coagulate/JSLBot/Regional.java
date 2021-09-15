package net.coagulate.JSLBot;

import net.coagulate.JSLBot.Handlers.ObjectData;
import net.coagulate.JSLBot.Packets.Messages.ParcelPropertiesRequestByID;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.S32;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class that holds per region mutable data.
 * A not very interesting mostly container class.
 *
 * @author Iain Price
 */
public class Regional {


	private final Circuit circuit;
	// map of local object ids
	private final Map<Integer,ObjectData> objects=new HashMap<>();
	// which we allocate using this lock here
	private final Object requestidlock=new Object();
	// map of parcel lookup requests to parcel local ids
	private final Map<Integer,Integer> requestresponses=new HashMap<>();
	// map of local parcel ids to parceldata structures
	private final Map<Integer,ParcelData> localparcelids=new HashMap<>();
	private final byte[][] parcelgrid=new byte[64][64];
	// map of local agents
	private Map<LLUUID,LLVector3> coarseagentlocationmap=new HashMap<>();
	// store parcel related data
	// each parcel request gets a unique ID
	private int parceldetailsrequestid;
	private long dayusec;
	@Nullable
	private LLVector3 sundirection;
	private float sunphase;


	public Regional(final Circuit c) {
		circuit=c;
	}

	// ---------- INSTANCE ----------
	public Circuit circuit() { return circuit; }

	public long handle() { return circuit.handle(); }

	public void killObject(final int value) { synchronized (objects) { objects.remove(value); } }

	/**
	 * Does an object exist by local ID?
	 *
	 * @param id The LOCAL ID of the object
	 *
	 * @return True if it is known to us
	 */
	public boolean hasObject(final Integer id) {
		synchronized (objects) {
			return objects.containsKey(id);
		}
	}

	/**
	 * Get an objects data by local id.
	 *
	 * @param id The LOCAL ID of the object
	 *
	 * @return The appropriate object data, potentially new and blank.
	 */
	public ObjectData getObject(final int id) {
		synchronized (objects) {
			if (!objects.containsKey(id)) { objects.put(id,new ObjectData(circuit.bot(),id)); }
			return objects.get(id);
		}
	}

	/**
	 * Get an objects data by UUID.
	 *
	 * @param uuid The in world UUID of the object
	 *
	 * @return The appropriate object data, or null if not found
	 */
	@Nullable
	public ObjectData getObject(final LLUUID uuid) {
		synchronized (objects) {
			for (final ObjectData od: objects.values()) {
				if (od.fullid!=null && od.fullid.equals(uuid)) {
					return od;
				}
			}
		}
		return null;
	}

	/**
	 * Get all the locally known objects.
	 *
	 * @return Set of LOCAL IDs
	 */
	@Nonnull
	public Set<Integer> getObjects() { return objects.keySet(); }

	public void setCoarseAgentLocations(final Map<LLUUID,LLVector3> locmap) { coarseagentlocationmap=locmap; }

	/**
	 * Generate a unique ID for requests.
	 *
	 * @return Unique int for this brain
	 */
	public int getRequestId() {
		synchronized (requestidlock) {
			parceldetailsrequestid++;
			return parceldetailsrequestid;
		}
	}

	/**
	 * Locate a parcel by local ID.
	 *
	 * @param get Parcel LOCAL ID
	 *
	 * @return Parcel Data, freshly created if necessary.
	 */
	public ParcelData getParcel(final int get) {
		if (!localparcelids.containsKey(get)) { localparcelids.put(get,new ParcelData(get,this)); }
		return localparcelids.get(get);
	}

	public Integer getResponse(final int requestid) { return requestresponses.get(requestid); }

	public void requestResponse(final int sequenceid,
	                            final int get) { requestresponses.put(sequenceid,get); }

	public void setParcelMap(final int sequence,
	                         final byte b) { parcelgrid[sequence/64][sequence%64]=b; }

	@Nonnull
	public String dumpParcels() {
		final Map<Byte,Integer> sizes=new HashMap<>();
		for (int x=0;x<64;x++) {
			for (int y=0;y<64;y++) {
				final byte id=parcelgrid[x][y];
				if (!sizes.containsKey(id)) {
					sizes.put(id,0);
				}
				sizes.put(id,sizes.get(id)+16);
			}
		}
		int totalsize=0;
		final StringBuilder resp=new StringBuilder();
		for (final Map.Entry<Byte,Integer> entry: sizes.entrySet()) {
			resp.append("\n");
			resp.append("#byeid#").append(((int) (byte) entry.getKey())&0xff);
			resp.append(" ").append(entry.getValue()).append("m2");
			totalsize+=entry.getValue();
		}
		return resp.toString();
	}

	public String getName() {
		return Global.regionName(handle());
	}

	public long getDayUSec() { return dayusec; }

	public void setDayUSec(final long dayusec) { this.dayusec=dayusec; }

	@Nullable
	public LLVector3 getSunDirection() { return sundirection; }

	public void setSunDirection(final LLVector3 sundirection) { this.sundirection=sundirection; }

	public float getSunPhase() { return sunphase; }

	public void setSunPhase(final float sunphase) { this.sunphase=sunphase; }

	@Nonnull
	public String dump() {
		String d="";
		if (circuit==bot().circuit()) { d="[PRIMARY] "; }
		d+="("+Global.regionName(circuit.handle())+") ";
		d+=coarseagentlocationmap.size()+" agents, "+objects.size()+" objects";
		return d;
	}

	@Nonnull
	@Override
	public String toString() { return circuit+"/Regional"; }

	// ----- Internal Instance -----
	@Nonnull
	private JSLBot bot() { return circuit.bot(); }

	public static class ParcelData {

		public final int id;
		@Nonnull
		public final Regional region;
		public boolean requested;
		public boolean populated;
		public int ownerprims=-1;
		public int groupprims=-1;
		public int otherprims=-1;
		@Nullable
		public String musicurl;
		@Nullable
		public LLUUID group;
		@Nullable
		public String name;
		@Nullable
		public String description;
		public int claimdate=-1;
		@Nullable
		public String mediaurl;
		public boolean seeavs=true;
		public int area=-1;
		@Nullable
		public LLUUID owner;
		public int primsused=-1;
		public int category=-1;
		public int autoreturntime=-1;
		public int simwidemaxprims=-1;
		public byte[] parcelflags=new byte[0];
		public int landingtype=-1;
		public int maxprims=-1;
		public int simwidetotalprims=-1;

		public ParcelData(final int id,
		                  @Nonnull final Regional region) {
			this.id=id;
			this.region=region;
		}

		// ---------- INSTANCE ----------
		@Nonnull
		@Override
		public String toString() {
			String ret="";
			ret+=region.getName()+"#"+(id&0xff);
			if (populated) {
				ret+=" '"+name+"' ("+description+") prims:"+ownerprims+"/"+groupprims+"/"+otherprims+"="+primsused+" of "+maxprims+" (simmax:"+simwidetotalprims+") Privacy:"+(!seeavs)+" claimed "+new Date(
						((long) (claimdate))*1000);
			}
			return ret;
		}

		public void populate() { populate(false); }

		public void populate(final boolean force) {
			if (requested && !force) { return; }
			requested=true;
			final ParcelPropertiesRequestByID req=new ParcelPropertiesRequestByID();
			req.bagentdata.vagentid=region.bot().getUUID();
			req.bagentdata.vsessionid=region.bot().getSession();
			req.bparceldata.vlocalid=new S32(id);
			req.bparceldata.vsequenceid=new S32();
			req.bparceldata.vsequenceid.value=(0xff&id);
			region.circuit().send(req,true);
		}

	}
}
