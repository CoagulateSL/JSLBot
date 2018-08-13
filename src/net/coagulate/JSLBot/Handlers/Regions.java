package net.coagulate.JSLBot.Handlers;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Debug;
import net.coagulate.JSLBot.Global;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.JSLBot.ParamHelp;
import net.coagulate.JSLBot.LLSD.LLSDArray;
import net.coagulate.JSLBot.LLSD.LLSDBinary;
import net.coagulate.JSLBot.LLSD.LLSDBoolean;
import net.coagulate.JSLBot.LLSD.LLSDInteger;
import net.coagulate.JSLBot.LLSD.LLSDMap;
import net.coagulate.JSLBot.LLSD.LLSDString;
import net.coagulate.JSLBot.LLSD.LLSDUUID;
import static net.coagulate.JSLBot.Log.debug;
import net.coagulate.JSLBot.Packets.Messages.MapBlockReply;
import net.coagulate.JSLBot.Packets.Messages.MapBlockReply_bData;
import net.coagulate.JSLBot.Packets.Messages.MapNameRequest;
import net.coagulate.JSLBot.Packets.Messages.ParcelOverlay;
import net.coagulate.JSLBot.Packets.Messages.ParcelPropertiesRequest;
import net.coagulate.JSLBot.Packets.Messages.SimulatorViewerTimeMessage;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.U64;
import net.coagulate.JSLBot.Packets.Types.Variable1;
import net.coagulate.JSLBot.Regional;
import net.coagulate.JSLBot.Regional.ParcelData;
import net.coagulate.JSLBot.UDPEvent;
import net.coagulate.JSLBot.XMLEvent;

/** Region based information parser
 *
 * @author Iain Price <git@predestined.net>
 */
public class Regions extends Handler {

    public Regions(JSLBot bot,Configuration conf) {
        super(bot,conf);
    }
    @Override
    public String toString() {
        return "Region tracking and manager, required for bot initiated Teleportation";
    }

    @Override
    public void initialise() throws Exception {
        bot.addCommand("lookup", this);
        bot.addImmediateUDP("MapBlockReply", this);
        bot.addCommand("list",this);
        bot.addCommand("parcels",this);
        bot.addCommand("id",this);
        bot.addImmediateUDP("ParcelOverlay",this);
        bot.addImmediateXML("ParcelProperties",this);
        bot.addImmediateUDP("SimulatorViewerTimeMessage",this);
    }

    private Object signal=new Object();
    private void mapBlockReply(MapBlockReply p,Regional regionid) {
        //System.out.println(p.dump());
        for (MapBlockReply_bData data:p.bdata) {
            // for some reason we get multiple replies, one has no access and 0 X/Y for a not found, not sure what this is about :)
            if (data.vaccess.value!=-1 && data.vx.value!=0 && data.vy.value!=0) {
                U64 handle=new U64();
                handle.value=data.vx.value;
                handle.value=handle.value<<(32+8);
                handle.value=handle.value | (data.vy.value<<8);
                if (handle.value!=0) { Global.regionName(handle.value,data.vname.toString()); }
                if (Debug.REGIONHANDLES) { debug(bot,"Map Block Reply computed handle "+Long.toUnsignedString(handle.value)); }
                Global.regionName(handle.value,data.vname.toString());
            }
        }
        synchronized(signal) {
            signal.notifyAll();
        }        
    }
    // done at circuit level
    //private void regionHandshake (RegionHandshake rh,Regional regionid) {
    //    Global.setRegionName(regionid.handle(),rh.bregioninfo.vsimname.toString());
    //}


    @Override
    public void loggedIn() throws Exception {
        
    }

    private void parcelOverlay(ParcelOverlay parceloverlay, Regional r) throws IOException {
        int quadrant=parceloverlay.bparceldata.vsequenceid.value;
        int sequence=quadrant*1024;
        for (int i=0;i<1024;i++) {
            byte id=parceloverlay.bparceldata.vdata.value[i];
            r.setParcelId(sequence,id);
            sequence++;
            Regional.ParcelData parcel = r.getParcel(id);
        }
    }

    private void parcelProperties(LLSDMap body, Regional region) throws IOException {
        LLSDArray array=(LLSDArray)body.get("ParcelData");
        LLSDMap data=(LLSDMap)array.get().get(0);        
        //System.out.println(data.toXML());
        LLSDInteger parcelid=(LLSDInteger) data.get("LocalID");
        //System.out.println("Got parcel id "+parcelid.toString());
        ParcelData parcel=region.getParcel((byte) parcelid.get());
        int sequenceid = ((LLSDInteger)data.get("SequenceID")).get();
        requestresponses.put(sequenceid,parcelid.get());
        synchronized(requestresponses) { requestresponses.notifyAll(); }
        parcel.ownerprims=((LLSDInteger)data.get("OwnerPrims")).get();
        parcel.groupprims=((LLSDInteger)data.get("GroupPrims")).get();
        parcel.otherprims=((LLSDInteger)data.get("OtherPrims")).get();
        parcel.musicurl=((LLSDString)data.get("MusicURL")).toString();
        parcel.group=((LLSDUUID)data.get("GroupID")).toLLUUID();
        parcel.name=((LLSDString)data.get("Name")).toString();
        parcel.description=((LLSDString)data.get("Desc")).toString();
        parcel.claimdate=((LLSDInteger)data.get("ClaimDate")).get();
        parcel.mediaurl=((LLSDString)data.get("MediaURL")).toString();
        parcel.seeavs=((LLSDBoolean)data.get("SeeAVs")).get();
        parcel.area=((LLSDInteger)data.get("Area")).get();
        parcel.owner=((LLSDUUID)data.get("OwnerID")).toLLUUID();
        parcel.primsused=((LLSDInteger)data.get("TotalPrims")).get();
        parcel.category=((LLSDInteger)data.get("Category")).get();
        parcel.autoreturntime=((LLSDInteger)data.get("OtherCleanTime")).get();
        parcel.simwidemaxprims=((LLSDInteger)data.get("SimWideMaxPrims")).get();
        parcel.parcelflags=((LLSDBinary)data.get("ParcelFlags")).toByte();
        parcel.landingtype=((LLSDInteger)data.get("LandingType")).get();
        parcel.maxprims=((LLSDInteger)data.get("MaxPrims")).get();
        parcel.simwidetotalprims=((LLSDInteger)data.get("SimWideTotalPrims")).get();
        parcel.populated=true;
        //System.out.println(parcel);
    }

    private void simTime(SimulatorViewerTimeMessage time, Regional region) {
        region.setDayUSec(time.btimeinfo.vusecsincestart.value);
        region.setSunDirection(time.btimeinfo.vsundirection);
        region.setSunPhase(time.btimeinfo.vsunphase.value);
    }

    @Override
    public void processImmediateUDP(Regional region, UDPEvent event, String eventname) throws Exception {
        if (eventname.equals("MapBlockReply")) { mapBlockReply((MapBlockReply)event.body(),region); }    
        if (eventname.equals("ParcelOverlay")) { parcelOverlay((ParcelOverlay)event.body(),region); }
        if (eventname.equals("SimulatorViewerTimeMessage")) { simTime((SimulatorViewerTimeMessage)event.body(),region); }
    }

    @Override
    public void processImmediateXML(Regional region, XMLEvent event, String eventname) throws Exception {
        if (eventname.equals("ParcelProperties")) { parcelProperties(event.map(),region); }        
    }

    @Override
    public void processUDP(Regional region, UDPEvent event, String eventname) throws Exception {
    }

    @Override
    public void processXML(Regional region, XMLEvent event, String eventname) throws Exception {
    }


    @CmdHelp(description = "Look up a region handle from a region name")
    public String lookupCommand(Regional region,
            @ParamHelp(description="Name of region to lookup")
            String name) throws IOException {
        if (name==null || name.equals("")) { return "No NAME parameter passed."; }
        Long cached=Global.regionHandle(name);
        MapNameRequest request=new MapNameRequest();
        request.bagentdata.vagentid=bot.getUUID();
        request.bagentdata.vsessionid=bot.getSession();
        request.bnamedata.vname=new Variable1(name);
        bot.send(request);
        Date now=new Date();
        while (Global.regionHandle(name)==null && ((new Date().getTime()-(now.getTime()))<5000)) {
            try { synchronized(signal) { signal.wait(1000); } } catch (InterruptedException e) {}
        }
        cached=Global.regionHandle(name);
        if (cached!=null) {
            return Long.toUnsignedString(cached);
        }
        return "Lookup failed";
    }
    @CmdHelp(description="List regions currently known to the botz")
    public String listCommand(Regional region) {
        String response="\n";
        for (Regional regional:bot.getRegionals()) {
            response+=Long.toUnsignedString(regional.handle());
            // DONT ASK :P
            //response+=" [[";
            //Long inverted=handle>>32;
            //inverted+=(handle&0xffffffff)<<32;
            //response+=Long.toUnsignedString(inverted)+"]]";
            response+=": "+regional.dump()+"\n";
        }
        return response;
    }
    public String parcelsCommand(Regional region) {
        return "Region: "+bot.getRegional().getName()+bot.getRegional().dumpParcels();
   }
    
    Map<Integer,Integer> requestresponses=new HashMap<>();
    private Object requestidlock=new Object();
    private int parceldetailsrequestid=0;
    private int getRequestId() { synchronized(requestidlock) {  parceldetailsrequestid++; return parceldetailsrequestid; } }
    public String idCommand(Regional region,String x,String y) throws IOException {
        int reqid=getRequestId();
        ParcelPropertiesRequest prr=new ParcelPropertiesRequest();
        prr.bagentdata.vagentid=bot.getUUID();
        prr.bagentdata.vsessionid=bot.getSession();
        prr.bparceldata.vsequenceid=new S32(reqid);
        prr.bparceldata.vnorth=new F32(Float.parseFloat(y));
        prr.bparceldata.vsouth=new F32(Float.parseFloat(y));
        prr.bparceldata.veast=new F32(Float.parseFloat(x));
        prr.bparceldata.vwest=new F32(Float.parseFloat(x));
        bot.send(prr,true);
        long expire=new Date().getTime()+5000;
        while (!requestresponses.containsKey(reqid) && expire>(new Date().getTime())) {
            synchronized(requestresponses) { try { requestresponses.wait(1000); }  catch (InterruptedException e) {}}
        }
        if (requestresponses.containsKey(reqid)) { return requestresponses.get(reqid)+""; }
        return "";
    }

}