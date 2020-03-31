package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.*;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.JSLBot.ParamHelp;
import net.coagulate.JSLBot.LLSD.*;
import net.coagulate.JSLBot.Packets.Messages.*;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.U64;
import net.coagulate.JSLBot.Packets.Types.Variable1;
import net.coagulate.JSLBot.Regional.ParcelData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Date;

/**
 * Process region queries.
 *
 * @author Iain Price
 */
public class Regions extends Handler {

	// signal
	private final Object mapblockreplysignal=new Object();

	/////////////////////////////// MAP BLOCK LOOKUP ( REGION HANDLE FROM REGION NAME )
	// signal for incoming parcel properties
	private final Object parcelpropertiessignal=new Object();

	public Regions(@Nonnull final JSLBot bot,
	               final Configuration conf) { super(bot,conf); }

	// ---------- INSTANCE ----------
	@CmdHelp(description="Look up a region handle from a region name")
	public String regionLookupCommand(final CommandEvent command,
	                                  @Nullable @ParamHelp(description="Name of region to lookup") final String name) {
		if (name==null || "".equals(name)) { return "No NAME parameter passed."; }
		// check cache
		Long cached=Global.regionHandle(name);
		if (cached!=null) { return Long.toUnsignedString(cached); }
		// issue request
		final MapNameRequest request=new MapNameRequest(bot);
		request.bnamedata.vname=new Variable1(name);
		bot.send(request);
		// sleep on the signal, checking for a result when woken
		final Date now=new Date();
		while (Global.regionHandle(name)==null && ((new Date().getTime()-(now.getTime()))<5000)) {
			try {
				synchronized (mapblockreplysignal) { mapblockreplysignal.wait(1000); }
			}
			catch (@Nonnull final InterruptedException e) {}
		}
		cached=Global.regionHandle(name);
		if (cached!=null) { return Long.toUnsignedString(cached); }
		return "Lookup failed";
	}


	////////////////////////////// Parcel Overlay
	// is just sent to us, so we stash it
	// I think it's used to locate parcels, which can be of any odd size essentially
	// note the "byte" id used here is not related to the parcel's local ID...

	/**
	 * Process a map block reply into a region handle, store globally, and signal any waiting threads.
	 * Must be immediate mode so it can signal delayed mode handlers.
	 *
	 * @param event Event
	 */
	public void mapBlockReplyUDPImmediate(@Nonnull final UDPEvent event) {
		final MapBlockReply p=(MapBlockReply) event.body();
		for (final MapBlockReply_bData data: p.bdata) {
			// for some reason we get multiple replies, one has no access and 0 X/Y for a not found, not sure what this is about :)
			if (data.vaccess.value!=-1 && data.vx.value!=0 && data.vy.value!=0) {
				final U64 handle=new U64();
				handle.value=data.vx.value;
				handle.value=handle.value<<(32+8);
				handle.value=handle.value|(data.vy.value<<8);
				if (handle.value!=0) { Global.regionName(handle.value,data.vname.toString()); }
				if (Debug.REGIONHANDLES) {
					log.fine("Map Block Reply computed handle "+Long.toUnsignedString(handle.value));
				}
				Global.regionName(handle.value,data.vname.toString());
			}
		}
		synchronized (mapblockreplysignal) {
			mapblockreplysignal.notifyAll();
		}
	}

	public void parcelOverlayUDPImmediate(@Nonnull final UDPEvent event) {
		final ParcelOverlay parceloverlay=(ParcelOverlay) event.body();
		final int quadrant=parceloverlay.bparceldata.vsequenceid.value;
		int sequence=quadrant*1024;
		for (int i=0;i<1024;i++) {
			final byte id=parceloverlay.bparceldata.vdata.value[i];
			event.region().setParcelMap(sequence,id);
			sequence++;
		}
	}


	/////////////////////// Parcel Properties
	// Getting info about a parcel

	@Nonnull
	@CmdHelp(description="Attempt to compute parcels and their size based on the overlay map, which may or may not work")
	public String parcelListCommand(final CommandEvent command) {
		return "Region: "+bot.getRegional().getName()+bot.getRegional().dumpParcels();
	}

	@Nonnull
	@CmdHelp(description="Get a parcel's LocalID from region-local x and y co-ordinates")
	public String parcelIdCommand(@Nonnull final CommandEvent command,
	                              @Nonnull @ParamHelp(description="X co-ordinate within the parcel") final String x,
	                              @Nonnull @ParamHelp(description="Y co-ordinate within the parcel") final String y) {
		final Regional region=command.region();
		final int reqid=region.getRequestId();
		final ParcelPropertiesRequest prr=new ParcelPropertiesRequest(bot); // set up the request
		prr.bparceldata.vsequenceid=new S32(reqid);
		prr.bparceldata.vnorth=new F32(Float.parseFloat(y));
		prr.bparceldata.vsouth=new F32(Float.parseFloat(y));
		prr.bparceldata.veast=new F32(Float.parseFloat(x));
		prr.bparceldata.vwest=new F32(Float.parseFloat(x));
		region.circuit().send(prr,true);
		final long expire=new Date().getTime()+5000; // sleep on the signal
		while (region.getResponse(reqid)==null && expire>(new Date().getTime())) {
			synchronized (parcelpropertiessignal) {
				try { parcelpropertiessignal.wait(1000); } catch (@Nonnull final InterruptedException e) {}
			}
		}
		if (region.getResponse(reqid)!=null) { return region.getResponse(reqid)+""; }
		return "";
	}

	public void parcelPropertiesXMLImmediate(@Nonnull final XMLEvent event) {
		final LLSDMap body=event.map();
		final LLSDArray array=(LLSDArray) body.get("ParcelData");
		final LLSDMap data=(LLSDMap) array.get().get(0);
		//System.out.println(data.toXML());
		final LLSDInteger parcelid=(LLSDInteger) data.get("LocalID");
		//System.out.println("Got parcel id "+parcelid.toString());
		final ParcelData parcel=event.region().getParcel(parcelid.get());
		final int sequenceid=((LLSDInteger) data.get("SequenceID")).get();
		event.region().requestResponse(sequenceid,parcelid.get());
		parcel.ownerprims=((LLSDInteger) data.get("OwnerPrims")).get();
		parcel.groupprims=((LLSDInteger) data.get("GroupPrims")).get();
		parcel.otherprims=((LLSDInteger) data.get("OtherPrims")).get();
		parcel.musicurl=data.get("MusicURL").toString();
		parcel.group=((LLSDUUID) data.get("GroupID")).toLLUUID();
		parcel.name=data.get("Name").toString();
		parcel.description=data.get("Desc").toString();
		parcel.claimdate=((LLSDInteger) data.get("ClaimDate")).get();
		parcel.mediaurl=data.get("MediaURL").toString();
		parcel.seeavs=((LLSDBoolean) data.get("SeeAVs")).get();
		parcel.area=((LLSDInteger) data.get("Area")).get();
		parcel.owner=((LLSDUUID) data.get("OwnerID")).toLLUUID();
		parcel.primsused=((LLSDInteger) data.get("TotalPrims")).get();
		parcel.category=((LLSDInteger) data.get("Category")).get();
		parcel.autoreturntime=((LLSDInteger) data.get("OtherCleanTime")).get();
		parcel.simwidemaxprims=((LLSDInteger) data.get("SimWideMaxPrims")).get();
		parcel.parcelflags=((LLSDBinary) data.get("ParcelFlags")).toByte();
		parcel.landingtype=((LLSDInteger) data.get("LandingType")).get();
		parcel.maxprims=((LLSDInteger) data.get("MaxPrims")).get();
		parcel.simwidetotalprims=((LLSDInteger) data.get("SimWideTotalPrims")).get();
		parcel.populated=true;
		//System.out.println(parcel);
		synchronized (parcelpropertiessignal) { parcelpropertiessignal.notifyAll(); }
	}


	////////////////////// misc regional data

	public void simulatorViewerTimeMessageUDPImmediate(@Nonnull final UDPEvent event) {
		final Regional region=event.region();
		final SimulatorViewerTimeMessage time=(SimulatorViewerTimeMessage) event.body();
		region.setDayUSec(time.btimeinfo.vusecsincestart.value);
		region.setSunDirection(time.btimeinfo.vsundirection);
		region.setSunPhase(time.btimeinfo.vsunphase.value);
	}

	@Nonnull
	@CmdHelp(description="List regions currently known to the botz")
	public String regionListCommand(final CommandEvent command) {
		final StringBuilder response=new StringBuilder("\n");
		for (final Regional regional: bot.getRegionals()) {
			response.append(Long.toUnsignedString(regional.handle())).append(": ").append(regional.dump()).append("\n");
		}
		return response.toString();
	}
}
