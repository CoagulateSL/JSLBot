package net.coagulate.JSLBot;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.coagulate.JSLBot.Handlers.ObjectData;
import net.coagulate.JSLBot.Packets.Messages.ParcelPropertiesRequestByID;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.S32;

/**  Class that holds regional level data.
 * A not very interesting mostly container class.
 * @author Iain Price <git@predestined.net>
 */
public class Regional {

    private Circuit circuit;
    public Regional(Circuit c) {
        circuit=c;
    }
    
    
    private Map<Integer,ObjectData> objects=new HashMap<>();
    public boolean hasObject(int id) {
        synchronized(objects) { if (objects.containsKey(id)) { return true; } return false; }
    }
    public ObjectData getObject(int id) {
        synchronized (objects) {
            if (!objects.containsKey(id)) { objects.put(id,new ObjectData(circuit.bot())); }
            return objects.get(id);
        }
    }
    public ObjectData getObject(LLUUID uuid) {
        synchronized (objects) {
            for (ObjectData od:objects.values()) {
                if (od.fullid!=null && od.fullid.equals(uuid)) {
                    return od;
                }
            }
        }
        return null;
    }
    public Set<Integer> getObjects() {
        return objects.keySet();
    }    
    public void killObject(int value) {
        synchronized(objects) {
            //if (!objects.containsKey(value)) { debug(bot,"Removing object we didn't know anything about"); }
            //if (objects.containsKey(value)) { debug(bot,"Removing object we did know something about"); }
            objects.remove(value);
        }
    }


    private Map<LLUUID, LLVector3> coarseagentlocationmap=new HashMap<>();
    public void setCoarseAgentLocations(Map<LLUUID, LLVector3> locmap) {
        coarseagentlocationmap=locmap;
    }

    private JSLBot bot() { return circuit.bot(); }

    public Circuit circuit() {
        return circuit;
    }

    public String dump() {
        String d="";
        if (circuit==bot().primary) { d="[PRIMARY] "; }
        d+="("+Global.regionName(circuit.handle())+") ";
        d+=coarseagentlocationmap.size()+" agents, "+objects.size()+" objects";
        return d;
    }


    public Long handle() { return circuit.handle(); }
    
    private byte[][] parcelgrid=new byte[64][64];
    private Map<Byte,ParcelData> parceldata=new HashMap<>();
    public void setParcelId(int sequence, byte b) {
        parcelgrid[sequence/64][sequence%64]=b;
        if (!parceldata.containsKey(b)) { synchronized(parceldata) { parceldata.put(b,new ParcelData(b,this)); } }
    }
    public ParcelData getParcel(byte parcelid) {
        if (!parceldata.containsKey(parcelid)) { synchronized(parceldata) { parceldata.put(parcelid,new ParcelData(parcelid,this)); } }
        return parceldata.get(parcelid);
    }

    public String dumpParcels() {
        Map<Byte,Integer> sizes=new HashMap<>();
        for (int x=0;x<64;x++) {
            for (int y=0;y<64;y++) {
                byte id=parcelgrid[x][y];
                if (!sizes.containsKey(id)) {
                    sizes.put(id,0);
                }
                sizes.put(id,sizes.get(id)+16);
            }
        }
        int totalsize=0;
        String resp="";
        for (byte id:sizes.keySet()) {
            resp+="\n";
            resp+="#"+(((int)id)&0xff);
            resp+=" "+sizes.get(id)+"m2";
            totalsize+=sizes.get(id);
        }
        return resp;
    }

    public String getName() {
        return Global.regionName(handle());
    }

    private long dayusec=0;
    public long getDayUSec() { return dayusec; }
    public void setDayUSec(long dayusec) { this.dayusec = dayusec; }

    private LLVector3 sundirection=null;
    public LLVector3 getSunDirection() { return sundirection; }
    public void setSunDirection(LLVector3 sundirection) { this.sundirection = sundirection; }

    private float sunphase=0;
    public float getSunPhase() { return sunphase; }
    public void setSunPhase(float sunphase) { this.sunphase = sunphase; }



    public class ParcelData {

        public boolean requested=false;
        public boolean populated=false;
        public byte id=0;
        public Regional region=null;
        public int ownerprims=-1;
        public int groupprims=-1;
        public int otherprims=-1;
        public String musicurl=null;
        public LLUUID group=null;
        public String name=null;
        public String description=null;
        public int claimdate=-1;
        public String mediaurl=null;
        public Boolean seeavs=null;
        public int area=-1;
        public LLUUID owner=null;
        public int primsused=-1;
        public int category=-1;
        public int autoreturntime=-1;
        public int simwidemaxprims=-1;
        public byte[] parcelflags=new byte[0];
        public int landingtype=-1;
        public int maxprims=-1;
        public int simwidetotalprims=-1;
        
        public String toString() {
            String ret="";
            ret+=region.getName()+"#"+(((int)id)&0xff);
            if (populated) {
                ret+=" '"+name+"' ("+description+") prims:"+ownerprims+"/"+groupprims+"/"+otherprims+"="+primsused+" of "+maxprims+" (simmax:"+simwidetotalprims+") Privacy:"+(!seeavs)+" claimed "+new Date(((long)(claimdate))*1000).toString();            }
            return ret;
        }
        
        public ParcelData(byte id,Regional region) {
            this.id=id; this.region=region;
        }
        public void populate() throws IOException { populate(false); }
        public void populate(boolean force) throws IOException {
            if (requested==true && force==false) { return; }
            requested=true;
            ParcelPropertiesRequestByID req=new ParcelPropertiesRequestByID();
            req.bagentdata.vagentid=region.bot().getUUID();
            req.bagentdata.vsessionid=region.bot().getSession();
            req.bparceldata.vlocalid=new S32();
            req.bparceldata.vlocalid.value=(0xff&((int)id));
            req.bparceldata.vsequenceid=new S32();
            req.bparceldata.vsequenceid.value=(0xff&((int)id));
            region.circuit().send(req,true);
        }
        
    }

}
