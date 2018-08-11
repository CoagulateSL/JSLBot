package net.coagulate.JSLBot.Handlers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import net.coagulate.JSLBot.BotUtils;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Debug;
import net.coagulate.JSLBot.Handler;
import static net.coagulate.JSLBot.Handlers.Objects.CompressedFlags.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.JSLBot.ParamHelp;
import static net.coagulate.JSLBot.Log.debug;
import static net.coagulate.JSLBot.Log.warn;
import net.coagulate.JSLBot.Packets.Messages.ImprovedTerseObjectUpdate;
import net.coagulate.JSLBot.Packets.Messages.ImprovedTerseObjectUpdate_bObjectData;
import net.coagulate.JSLBot.Packets.Messages.KillObject;
import net.coagulate.JSLBot.Packets.Messages.KillObject_bObjectData;
import net.coagulate.JSLBot.Packets.Messages.ObjectPropertiesFamily;
import net.coagulate.JSLBot.Packets.Messages.ObjectUpdate;
import net.coagulate.JSLBot.Packets.Messages.ObjectUpdateCached;
import net.coagulate.JSLBot.Packets.Messages.ObjectUpdateCached_bObjectData;
import net.coagulate.JSLBot.Packets.Messages.ObjectUpdateCompressed;
import net.coagulate.JSLBot.Packets.Messages.ObjectUpdateCompressed_bObjectData;
import net.coagulate.JSLBot.Packets.Messages.ObjectUpdate_bObjectData;
import net.coagulate.JSLBot.Packets.Messages.RequestMultipleObjects;
import net.coagulate.JSLBot.Packets.Messages.RequestMultipleObjects_bObjectData;
import net.coagulate.JSLBot.Packets.Messages.RequestObjectPropertiesFamily;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.LLQuaternion;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U16;
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
    
    public enum CompressedFlags {
        NONE(0x00), SCRACHPAD(0x01), TREE(0x02), TEXT(0x04), PARTICLES(0x08), SOUND(0x10), PARENT(0x20), TEXTUREANIM(0x40), ANGULARVEL(0x80), NAMEVALUE(0x100), MEDIAURL(0x200);
        private final int id;
        CompressedFlags(int id) { this.id=id; }
        public int getValue() { return id; }
    }

    
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
        bot.addImmediateUDP("ObjectUpdateCached",this);
        bot.addImmediateUDP("ObjectUpdateCompressed",this);
        bot.addCommand("list",this);
        bot.addCommand("roots",this);
        bot.addCommand("find",this);
        bot.addCommand("get",this);
        bot.addCommand("uuid",this);
    }

    @Override
    public void loggedIn() throws Exception {
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
        if (Debug.TRACKNEWOBJECTS) {
            if (ourobj.name==null || (!ourobj.name.equals(object.bobjectdata.vname.toString()))) {
                debug(bot,"New object named "+object.bobjectdata.vname.toString()+" in region "+r.getName());
            }
        }
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
            //System.out.println(object.toString());
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
        if (eventname.equals("ObjectUpdateCached")) { 
            objectUpdateCached((ObjectUpdateCached)event.body(),region);
        }
        if (eventname.equals("ObjectUpdateCompressed")) { 
            objectUpdateCompressed((ObjectUpdateCompressed)event.body(),region);
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

    @CmdHelp(description = "List all objects to the console (debugging only)")
    public String listCommand(Regional region) {
        for (Integer id:region.getObjects()) {
            System.out.println(region.getObject(id).toString());
        }
        return "Dumped to console because so many.";
    }
    @CmdHelp(description = "List all unowned objects to the console (debugging only)")
    public String rootsCommand(Regional region) {
        for (Integer id:region.getObjects()) {
            ObjectData o = region.getObject(id);
            if (o.parentid==null || o.parentid.value==0) {
                System.out.println(o.toString());
            }
        }
        return "Dumped to console because so many.";
    }
    @CmdHelp(description="Find an object by a name fragment")
    public String findCommand(Regional region,
            @ParamHelp(description="Fragment of a name to search for")
            String name) {
        ObjectData best=null;
        if (region==null) { throw new NullPointerException("Null region passed to Objects.Find"); }
        if (name==null) { return "Must supply 'name <text>' parameter"; }
        name=name.toLowerCase();
        for (Integer id:region.getObjects()) {
            ObjectData check = region.getObject(id);
            if (check.parentid==null || check.parentid.value==0) {
                if (check.name!=null) {
                    if (check.name.toLowerCase().indexOf(name)>=0) { best=check; }
                }
            }
        }
        if (best==null) { return "No such object found"; }
        return name+"->"+best.toString();            
    }

    private void objectUpdateCached(ObjectUpdateCached objectUpdateCached, Regional region) throws IOException {
        RequestMultipleObjects request=new RequestMultipleObjects();
        request.bagentdata.vagentid=bot.getUUID();
        request.bagentdata.vsessionid=bot.getSession();
        request.bobjectdata=new ArrayList<>();
        for (ObjectUpdateCached_bObjectData data:objectUpdateCached.bobjectdata) {
            int id=data.vid.value;
            int crc=data.vcrc.value;
            if (!region.hasObject(id)) {
                if (Debug.TRACKNEWOBJECTS) { debug(bot,"Cached update to unknown object "+id+" in region "+region.getName()); }
                RequestMultipleObjects_bObjectData reqod=new RequestMultipleObjects_bObjectData();
                reqod.vcachemisstype=new U8(0);
                reqod.vid=data.vid;
                request.bobjectdata.add(reqod);
            }
        }
        if (!request.bobjectdata.isEmpty()) { region.circuit().send(request,true); }
    }
 
    private void objectUpdateCompressed(ObjectUpdateCompressed ouc, Regional region) throws IOException {
        //debug(bot,ouc.dump());
        for (ObjectUpdateCompressed_bObjectData data:ouc.bobjectdata) {
            ByteBuffer buffer=ByteBuffer.wrap(data.vdata.value);
            LLUUID uuid=new LLUUID(buffer);
            int localid=new U32(buffer).value;
            byte pcode=buffer.get();
            boolean isnew=!(region.hasObject(localid));
            ObjectData object=region.getObject(localid);
            object.fullid=uuid;
            
            byte state=buffer.get();
            object.crc=new U32(buffer).value;
            byte materia=buffer.get();
            object.clickaction=new U8(buffer);
            object.scale=new LLVector3(buffer);
            LLVector3 position=new LLVector3(buffer);
            object.position(position.x, position.y, position.z);
            LLQuaternion rotation=new LLQuaternion(buffer);
            int compressedflags=new U32(buffer).value;
            object.owner=new LLUUID(buffer);
            if ((compressedflags & ANGULARVEL.getValue())==ANGULARVEL.getValue()) { LLQuaternion angularvel=new LLQuaternion(buffer); }
            if ((compressedflags & PARENT.getValue())==PARENT.getValue()) { object.parentid=new U32(buffer); }
            if ((compressedflags & TREE.getValue())==TREE.getValue()) { byte treespecies=buffer.get(); } else
            {
                if ((compressedflags & SCRACHPAD.getValue())==SCRACHPAD.getValue()) { String scratchpad=BotUtils.readZeroTerminatedString(buffer); }
            }
            if ((compressedflags & TEXT.getValue())==TEXT.getValue()) {object.floattext=BotUtils.readZeroTerminatedString(buffer);}
            if ((compressedflags & MEDIAURL.getValue())==MEDIAURL.getValue()) { String mediaurl=BotUtils.readZeroTerminatedString(buffer); }
            if ((compressedflags & PARTICLES.getValue())==PARTICLES.getValue()) { byte[] particles=new byte[86]; buffer.get(particles); }
            // extra parameters
            U8 extraparamcount=new U8(buffer);
            for (int i=0;i<extraparamcount.integer();i++) {
                U16 paramtype=new U16(buffer);
                U32 paramlength=new U32(buffer);
                byte[] param=new byte[paramlength.value];
                buffer.get(param);
            }
            //
            if ((compressedflags & SOUND.getValue())==SOUND.getValue()) { byte sound[]=new byte[16+4+4]; buffer.get(sound); }
            if ((compressedflags & NAMEVALUE.getValue())==NAMEVALUE.getValue()) { String namevalues=BotUtils.readZeroTerminatedString(buffer); 
                System.out.println("Compressed name values : "+namevalues);
            }
            
            
            
            //System.out.println("Compressed update for "+localid);
        }
    }
    
    @CmdHelp(description="Lookup or request a prim by UUID")
    public String uuidCommand(Regional region,
            @ParamHelp(description="Prim UUID")
            String uuid) throws IOException {
        LLUUID lluuid=new LLUUID(uuid);
        ObjectData od=region.getObject(lluuid);
        if (od==null) { return "Object not found by UUID"; }
        return od.toString();
    }
    
    
    @CmdHelp(description="Lookup or request a prim by local id")
    public String getCommand(Regional region,
            @ParamHelp(description="Local prim id (32 bit int)")
            String localid) throws IOException {
        int id=Integer.parseInt(localid);
        if (region.hasObject(id)) { return "Exists as "+region.getObject(id).name; }
        RequestMultipleObjects request=new RequestMultipleObjects();
        request.bagentdata.vagentid=bot.getUUID();
        request.bagentdata.vsessionid=bot.getSession();
        request.bobjectdata=new ArrayList<>();
        RequestMultipleObjects_bObjectData reqod=new RequestMultipleObjects_bObjectData();
        reqod.vcachemisstype=new U8(0);
        reqod.vid=new U32(id);
        request.bobjectdata.add(reqod);
        bot.send(request,true);
        return "Sent request to simulator";
    }

}