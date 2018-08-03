package net.coagulate.JSLBot.Handlers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import static net.coagulate.JSLBot.Log.warn;
import net.coagulate.JSLBot.Packets.Messages.ImprovedTerseObjectUpdate;
import net.coagulate.JSLBot.Packets.Messages.ImprovedTerseObjectUpdate_bObjectData;
import net.coagulate.JSLBot.Packets.Messages.KillObject;
import net.coagulate.JSLBot.Packets.Messages.KillObject_bObjectData;
import net.coagulate.JSLBot.Packets.Messages.ObjectPropertiesFamily;
import net.coagulate.JSLBot.Packets.Messages.ObjectUpdate;
import net.coagulate.JSLBot.Packets.Messages.ObjectUpdate_bObjectData;
import net.coagulate.JSLBot.Packets.Messages.RequestObjectPropertiesFamily;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U8;
import net.coagulate.JSLBot.Regional;
import net.coagulate.JSLBot.UDPEvent;
import net.coagulate.JSLBot.XMLEvent;

/** Processes messages about objects within the world
 *
 * @author Iain Price <git@predestined.net>
 */
public class Objects extends Handler {
    public Objects(JSLBot bot,Configuration c) { super(bot,c); }
    @Override
    public String toString() {
        return "Object tracking manager";
    }
    @Override
    public void initialise() throws Exception {
        bot.addImmediateUDP("ObjectUpdate", this);
        bot.addImmediateUDP("ImprovedTerseObjectUpdate", this);
        bot.addImmediateUDP("KillObject",this);
        bot.addImmediateUDP("ObjectPropertiesFamily",this);
        bot.addCommand("objects",this);
        bot.addCommand("findobject",this);
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

    @Override
    public void processImmediateUDP(Regional region, UDPEvent event, String eventname) throws Exception {
        if (eventname.equals("ObjectUpdate")) {
            processObjectData((ObjectUpdate) event.body(),region);
        }
        if (eventname.equals("KillObject")) {
            killObjects((KillObject)event.body(),region); 
        }
        if (eventname.equals("ObjectPropertiesFamily")) {
            objectProperties((ObjectPropertiesFamily)event.body(),region);
        }
        if (eventname.equals("ImprovedTerseObjectUpdate")) { 
            improvedTerseObjectUpdate((ImprovedTerseObjectUpdate)event.body(),region);
        }
    }

    @Override
    public void processImmediateXML(Regional region, XMLEvent event, String eventname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processUDP(Regional region, UDPEvent event, String eventname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processXML(Regional region, XMLEvent event, String eventname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String execute(Regional region, CommandEvent event, String eventname, Map<String,String> parameters) throws Exception {
        if (eventname.equalsIgnoreCase("objects")) {
            for (Integer id:region.getObjects()) {
                System.out.println(region.getObject(id).toString());
            }
            return "Dumped to console because so many.";
        }
        if (eventname.equalsIgnoreCase("findobject")) {
            ObjectData best=null;
            String searching=event.parameters().get("name");
            if (searching==null) { return "Must supply 'name <text>' parameter"; }
            searching=searching.toLowerCase();
            for (Integer id:region.getObjects()) {
                ObjectData check = region.getObject(id);
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

}
