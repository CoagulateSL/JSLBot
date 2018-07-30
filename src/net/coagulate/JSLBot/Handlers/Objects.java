package net.coagulate.JSLBot.Handlers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Event;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import static net.coagulate.JSLBot.Log.CRIT;
import static net.coagulate.JSLBot.Log.log;
import static net.coagulate.JSLBot.Log.warn;
import net.coagulate.JSLBot.Packets.Messages.CoarseLocationUpdate;
import net.coagulate.JSLBot.Packets.Messages.CoarseLocationUpdate_bAgentData;
import net.coagulate.JSLBot.Packets.Messages.CoarseLocationUpdate_bLocation;
import net.coagulate.JSLBot.Packets.Messages.ImprovedTerseObjectUpdate;
import net.coagulate.JSLBot.Packets.Messages.ImprovedTerseObjectUpdate_bObjectData;
import net.coagulate.JSLBot.Packets.Messages.KillObject;
import net.coagulate.JSLBot.Packets.Messages.KillObject_bObjectData;
import net.coagulate.JSLBot.Packets.Messages.ObjectPropertiesFamily;
import net.coagulate.JSLBot.Packets.Messages.ObjectUpdate;
import net.coagulate.JSLBot.Packets.Messages.ObjectUpdate_bObjectData;
import net.coagulate.JSLBot.Packets.Messages.RequestMultipleObjects;
import net.coagulate.JSLBot.Packets.Messages.RequestMultipleObjects_bObjectData;
import net.coagulate.JSLBot.Packets.Messages.RequestObjectPropertiesFamily;
import net.coagulate.JSLBot.Packets.Messages.RequestObjectPropertiesFamily_bObjectData;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U8;
import net.coagulate.JSLBot.Regional;

/** Processes messages about objects within the world
 *
 * @author Iain Price <git@predestined.net>
 */
public class Objects extends Handler {
    
    public Objects(Configuration c) { super(c); }
    @Override
    public String toString() {
        return "Object tracking manager";
    }
    JSLBot bot;
    @Override
    public void initialise(JSLBot ai) throws Exception {
        bot=ai;
        bot.addImmediateHandler("ObjectUpdate", this);
        bot.addImmediateHandler("ImprovedTerseObjectUpdate", this);
        bot.addImmediateHandler("KillObject",this);
        bot.addImmediateHandler("ObjectPropertiesFamily",this);
        bot.addCommand("objects",this);
        bot.addCommand("findobject",this);
    }

    @Override
    public void processImmediate(Event p) throws Exception {
        if (p.getName().equals("ObjectUpdate")) {
            processObjectData((ObjectUpdate) p.message(),p.getRegion());
        }
        if (p.getName().equals("KillObject")) {
            killObjects((KillObject)p.message(),p.getRegion()); 
        }
        if (p.getName().equals("ObjectPropertiesFamily")) {
            objectProperties((ObjectPropertiesFamily)p.message(),p.getRegion());
        }
        if (p.getName().equals("ImprovedTerseObjectUpdate")) { 
            improvedTerseObjectUpdate((ImprovedTerseObjectUpdate)p.message(),p.getRegion());
        }
    }

    @Override
    public void process(Event p) throws Exception {
    }

    @Override
    public String execute(String command, Map<String, String> parameters) throws Exception {
        Regional r=bot.getRegional();
        if (command.equalsIgnoreCase("objects")) {
            for (Integer id:r.getObjects()) {
                System.out.println(r.getObject(id).toString());
            }
            return "Dumped to console because so many.";
        }
        if (command.equalsIgnoreCase("findobject")) {
            ObjectData best=null;
            String searching=parameters.get("name");
            if (searching==null) { return "Must supply 'name <text>' parameter"; }
            searching=searching.toLowerCase();
            for (Integer id:r.getObjects()) {
                ObjectData check = r.getObject(id);
                if (check.parentid.value==0) {
                    if (check.name!=null) {
                        if (check.name.toLowerCase().indexOf(searching)>=0) { best=check; }
                    }
                }
            }
            if (best==null) { return "No such object found"; }
            return searching+"->"+best.toString();            
        }
        return "Unknown command";
    }

    @Override
    public void loggedIn() throws Exception {
    }

    @Override
    public String help(String command) {
        if (command.equalsIgnoreCase("objects")) { return "Dump object data (all objects) to console (because so many)"; }
        if (command.equalsIgnoreCase("findobject name <namefragment>")) { return "Find an object by name"; }
        return "Unknown command";
    }

    private void processObjectData(ObjectUpdate data,Regional r) throws IOException {
        // and request more info too
        //System.out.println(data.dump());
        for (ObjectUpdate_bObjectData obj:data.bobjectdata) {            
            ObjectData od=r.getObject(obj.vid.value);
            od.id=obj.vid;
            od.fullid=obj.vfullid;
            od.clickaction=obj.vclickaction;
            od.parentid=obj.vparentid;
            //System.out.println(obj.vnamevalue.toString());
            if (od.requested==false) {
                RequestObjectPropertiesFamily reqobjs=new RequestObjectPropertiesFamily();
                reqobjs.bagentdata.vagentid=bot.getUUID();
                reqobjs.bagentdata.vsessionid=bot.getSession();
                reqobjs.bobjectdata.vobjectid=obj.vfullid;
                reqobjs.bobjectdata.vrequestflags=obj.vid;  // believe we can use this as its passed back
                od.requested=true;
                r.circuit().send(reqobjs,true);
            }
        }
    }

    private void killObjects(KillObject killobject, Regional r) {
        List<KillObject_bObjectData> killlist = killobject.bobjectdata;
        for (KillObject_bObjectData kill:killlist) {
            r.killObject(kill.vid.value);
        }
    }

    private void objectProperties(ObjectPropertiesFamily object, Regional r) {
        int objectid=object.bobjectdata.vrequestflags.value;
        if (objectid==0) { warn(bot,"ObjectProperties request flags are zero, which should be the object id"); }
        ObjectData ourobj = r.getObject(objectid);
        ourobj.name=object.bobjectdata.vname.toString();
        ourobj.description=object.bobjectdata.vdescription.toString();
        ourobj.owner=object.bobjectdata.vownerid;
        ourobj.group=object.bobjectdata.vgroupid;
        ourobj.lastowner=object.bobjectdata.vlastownerid;
        
    }

    private void improvedTerseObjectUpdate(ImprovedTerseObjectUpdate msg, Regional region) {
        for (ImprovedTerseObjectUpdate_bObjectData obj:msg.bobjectdata) {
            byte data[]=obj.vdata.value; // this is not well documented, but based on code...
            //System.out.println("ITOU data length: "+data.length);
            ByteBuffer buffer = ByteBuffer.wrap(data);
            U32 id=new U32(buffer);
            ObjectData object = region.getObject(id.value);
            U8 state=new U8(buffer);
            U8 notavatar=new U8(buffer);
            boolean avatar=notavatar.value!=0;
            // collision plane here (?)
            if (avatar) { new U32(buffer);new U32(buffer);new U32(buffer);new U32(buffer); }
            F32 x=new F32();  x.read(buffer);
            F32 y=new F32();  y.read(buffer);
            F32 z=new F32();  z.read(buffer);
            //System.out.println("ID:"+id.value+" - "+object.name+" - "+region.hasObject(id.value)+" "+x.value+","+y.value+","+z.value);
            // 3*16bit vel
            // 3*16bit acceleration
            // 4*16bit rotation
            // 3*16bit rotation
            // none of which we care about :P
            object.agent=avatar;
            object.position(x.value,y.value,z.value);
        }
    }

}
