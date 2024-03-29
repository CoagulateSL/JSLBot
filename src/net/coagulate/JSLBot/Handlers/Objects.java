package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.*;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.JSLBot.Param;
import net.coagulate.JSLBot.Packets.Messages.*;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import static net.coagulate.JSLBot.Handlers.Objects.CompressedFlags.*;

/**
 * Processes messages and commands about objects within the world
 *
 * @author Iain Price
 */
public class Objects extends Handler {
	
	public Objects(@Nonnull final JSLBot bot,final Configuration config) {
		super(bot,config);
	}
	
	// ---------- INSTANCE ----------
	public void objectUpdateUDPImmediate(@Nonnull final UDPEvent event) {
		@Nonnull final ObjectUpdate data=(ObjectUpdate)event.body();
		// and request more info too
		//System.out.println(data.dump());
		for (@Nonnull final ObjectUpdate_bObjectData obj: data.bobjectdata) {
			final ObjectData od=event.region().getObject(obj.vid.value);
			od.id=obj.vid;
			od.fullid=obj.vfullid;
			od.clickaction=obj.vclickaction;
			od.parentid=obj.vparentid;
			//if (od.name!=null) { System.out.println("Updating object "+od.name); } else { System.out.println("Updating who knows what"); }
			//System.out.println(obj.vnamevalue.toString());
			if (!od.requested) {
				@Nonnull final RequestObjectPropertiesFamily reqobjs=new RequestObjectPropertiesFamily();
				reqobjs.bagentdata.vagentid=bot.getUUID();
				reqobjs.bagentdata.vsessionid=bot.getSession();
				reqobjs.bobjectdata.vobjectid=obj.vfullid;
				reqobjs.bobjectdata.vrequestflags=obj.vid;  // believe we can use this as its passed back
				od.requested=true;
				event.region().circuit().send(reqobjs,true);
			}
		}
	}
	
	public void killObjectUDPImmediate(@Nonnull final UDPEvent event) {
		@Nonnull final KillObject killobject=(KillObject)event.body();
		final List<KillObject_bObjectData> killlist=killobject.bobjectdata;
		for (@Nonnull final KillObject_bObjectData kill: killlist) {
			
			final ObjectData object=event.region().getObject(kill.vid.value);
			//if (object.name!=null) { System.out.println("Killing object "+object.name); } else { System.out.println("Killing who knows what"); }
			
			event.region().killObject(kill.vid.value);
		}
	}
	
	public void objectPropertiesFamilyUDPImmediate(@Nonnull final UDPEvent event) {
		@Nonnull final ObjectPropertiesFamily object=(ObjectPropertiesFamily)event.body();
		final int objectid=object.bobjectdata.vrequestflags.value;
		if (objectid==0) {
			log.warning("ObjectProperties request flags are zero, which should be the object id");
		}
		final ObjectData ourobj=event.region().getObject(objectid);
		if (Debug.TRACKNEWOBJECTS) {
			if (ourobj.name==null||(!ourobj.name.equals(object.bobjectdata.vname.toString()))) {
				log.finer("New object named "+object.bobjectdata.vname+" in region "+event.region().getName());
			}
		}
		ourobj.name=object.bobjectdata.vname.toString();
		ourobj.description=object.bobjectdata.vdescription.toString();
		ourobj.owner=object.bobjectdata.vownerid;
		ourobj.group=object.bobjectdata.vgroupid;
		ourobj.lastowner=object.bobjectdata.vlastownerid;
		
	}
	
	public void improvedTerseObjectUpdateUDPImmediate(@Nonnull final UDPEvent event) {
		@Nonnull final ImprovedTerseObjectUpdate msg=(ImprovedTerseObjectUpdate)event.body();
		for (@Nonnull final ImprovedTerseObjectUpdate_bObjectData obj: msg.bobjectdata) {
			@Nonnull final byte[] data=obj.vdata.value; // this is not well documented, but based on code...
			//System.out.println("ITOU data length: "+data.length);
			@Nonnull final ByteBuffer buffer=ByteBuffer.wrap(data);
			@Nonnull final U32 id=new U32(buffer);
			final ObjectData object=event.region().getObject(id.value);
			//if (object.name!=null) { System.out.println("TerseUpdating object "+object.name); } else { System.out.println("TerseUpdating who knows what"); }
			@Nonnull final U8 state=new U8(buffer);
			@Nonnull final U8 notavatar=new U8(buffer);
			final boolean avatar=notavatar.value!=0;
			// collision plane here (?)
			if (avatar) {
				new U32(buffer);
				new U32(buffer);
				new U32(buffer);
				new U32(buffer);
			}
			@Nonnull final F32 x=new F32();
			x.read(buffer);
			@Nonnull final F32 y=new F32();
			y.read(buffer);
			@Nonnull final F32 z=new F32();
			z.read(buffer);
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
	
	@Nonnull
	@CmdHelp(description="List all objects to the console (debugging only)")
	public String objectListCommand(@Nonnull final CommandEvent command) {
		@Nonnull final Regional region=command.region();
		for (final Integer id: region.getObjects()) {
			System.out.println(region.getObject(id));
		}
		return "Dumped to console because so many.";
	}
	
	@Nonnull
	@CmdHelp(description="List all unparented (root) objects to the console (debugging only)")
	public String objectRootsCommand(@Nonnull final CommandEvent command) {
		@Nonnull final Regional region=command.region();
		for (final Integer id: region.getObjects()) {
			final ObjectData o=region.getObject(id);
			if (o.parentid==null||o.parentid.value==0) {
				System.out.println(o);
			}
		}
		return "Dumped to console because so many.";
	}
	
	@Nonnull
	@CmdHelp(description="Find an object by a name fragment")
	public String objectFindCommand(@Nonnull final CommandEvent command,
	                                @Nonnull @Param(name="name", description="Fragment of a name to search for")
	                                String name) {
		@Nonnull final Regional region=command.region();
		@Nullable ObjectData best=null;
		name=name.toLowerCase();
		for (final Integer id: region.getObjects()) {
			final ObjectData check=region.getObject(id);
			if (check.parentid==null||check.parentid.value==0) {
				if (check.name!=null) {
					if (check.name.toLowerCase().contains(name)) {
						best=check;
					}
				}
			}
		}
		if (best==null) {
			return "No such object found";
		}
		return name+"->"+best;
	}
	
	public void objectUpdateCachedUDPImmediate(@Nonnull final UDPEvent event) {
		@Nonnull final ObjectUpdateCached objectUpdateCached=(ObjectUpdateCached)event.body();
		@Nonnull final RequestMultipleObjects request=new RequestMultipleObjects();
		request.bagentdata.vagentid=bot.getUUID();
		request.bagentdata.vsessionid=bot.getSession();
		request.bobjectdata=new ArrayList<>();
		for (@Nonnull final ObjectUpdateCached_bObjectData data: objectUpdateCached.bobjectdata) {
			final int id=data.vid.value;
			final int crc=data.vcrc.value;
			if (!event.region().hasObject(id)) {
				if (Debug.TRACKNEWOBJECTS) {
					log.finer("Cached update to unknown object "+id+" in region "+event.region().getName());
				}
				@Nonnull final RequestMultipleObjects_bObjectData reqod=new RequestMultipleObjects_bObjectData();
				reqod.vcachemisstype=new U8(0);
				reqod.vid=data.vid;
				request.bobjectdata.add(reqod);
			}
		}
		if (!request.bobjectdata.isEmpty()) {
			event.region().circuit().send(request,true);
		}
	}
	
	public void objectUpdateCompressedUDPImmediate(@Nonnull final UDPEvent event) {
		@Nonnull final ObjectUpdateCompressed ouc=(ObjectUpdateCompressed)event.body();
		@Nonnull final Regional region=event.region();
		for (@Nonnull final ObjectUpdateCompressed_bObjectData data: ouc.bobjectdata) {
			@Nonnull final ByteBuffer buffer=ByteBuffer.wrap(data.vdata.value);
			@Nonnull final LLUUID uuid=new LLUUID(buffer);
			final int localid=new U32(buffer).value;
			final byte pcode=buffer.get();
			final boolean isnew=!(region.hasObject(localid));
			final ObjectData object=region.getObject(localid);
			object.fullid=uuid;
			
			final byte state=buffer.get();
			object.crc=new U32(buffer).value;
			final byte materia=buffer.get();
			object.clickaction=new U8(buffer);
			object.scale=new LLVector3(buffer);
			@Nonnull final LLVector3 position=new LLVector3(buffer);
			object.position(position.x,position.y,position.z);
			@Nonnull final LLQuaternion rotation=new LLQuaternion(buffer);
			final int compressedflags=new U32(buffer).value;
			object.owner=new LLUUID(buffer);
			if ((compressedflags&ANGULARVEL.getValue())==ANGULARVEL.getValue()) {
				@Nonnull final LLQuaternion angularvel=new LLQuaternion(buffer);
			}
			if ((compressedflags&PARENT.getValue())==PARENT.getValue()) {
				object.parentid=new U32(buffer);
			}
			if ((compressedflags&TREE.getValue())==TREE.getValue()) {
				final byte treespecies=buffer.get();
			} else {
				if ((compressedflags&SCRACHPAD.getValue())==SCRACHPAD.getValue()) {
					@Nonnull final String scratchpad=BotUtils.readZeroTerminatedString(buffer);
				}
			}
			if ((compressedflags&TEXT.getValue())==TEXT.getValue()) {
				object.floattext=BotUtils.readZeroTerminatedString(buffer);
			}
			if ((compressedflags&MEDIAURL.getValue())==MEDIAURL.getValue()) {
				@Nonnull final String mediaurl=BotUtils.readZeroTerminatedString(buffer);
			}
			if ((compressedflags&PARTICLES.getValue())==PARTICLES.getValue()) {
				@Nonnull final byte[] particles=new byte[86];
				buffer.get(particles);
			}
			// extra parameters
            /*
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
            */
			
			
			//System.out.println("Compressed update for "+localid);
		}
	}
	
	@Nullable
	@CmdHelp(description="Lookup or request a prim by UUID")
	public String objectUUIDCommand(@Nonnull final CommandEvent command,
	                                @Param(name="uuid", description="Prim UUID") final String uuid) {
		@Nonnull final Regional region=command.region();
		@Nonnull final LLUUID lluuid=new LLUUID(uuid);
		@Nullable final ObjectData od=region.getObject(lluuid);
		if (od==null) {
			return "Object not found by UUID";
		}
		return od.toString();
	}
	
	@Nonnull
	@CmdHelp(description="Lookup or request a prim by local id")
	public String objectGetCommand(@Nonnull final CommandEvent command,
	                               @Nonnull @Param(name="localid", description="Local prim id (32 bit int)")
	                               final String localid) {
		@Nonnull final Regional region=command.region();
		final int id=Integer.parseInt(localid);
		if (region.hasObject(id)) {
			return "Exists as "+region.getObject(id).name;
		}
		@Nonnull final RequestMultipleObjects request=new RequestMultipleObjects();
		request.bagentdata.vagentid=bot.getUUID();
		request.bagentdata.vsessionid=bot.getSession();
		request.bobjectdata=new ArrayList<>();
		@Nonnull final RequestMultipleObjects_bObjectData reqod=new RequestMultipleObjects_bObjectData();
		reqod.vcachemisstype=new U8(0);
		reqod.vid=new U32(id);
		request.bobjectdata.add(reqod);
		bot.send(request,true);
		return "Sent request to simulator";
	}
	
	@Nonnull
	@CmdHelp(description="Return a prim by UUID or localid")
	public String objectReturnCommand(@Nonnull final CommandEvent command,
	                                  @Nullable @Param(name="primuuid", description="UUID of prim to return")
	                                  final String primuuid,
	                                  @Nullable @Param(name="localid", description="LocalID of prim to return")
	                                  final String localid) {
		@Nonnull final Regional region=command.region();
		// One way or another we need the local ID
		final int id;
		if (localid==null) {
			if (primuuid==null) {
				return "9 - You must supply Prim UUID or Local simulator ID";
			}
			// extract by primuuid
			@Nullable final LLUUID uuid=new LLUUID(primuuid);
			@Nullable final ObjectData od=region.getObject(uuid);
			if (od==null) {
				return "1 - Could not find object by UUID "+primuuid;
			}
			id=od.id.value;
		} else {
			id=Integer.parseInt(localid);
		}
		@Nonnull final DeRezObject dro=new DeRezObject();
		// conn into
		dro.bagentdata.vagentid=bot.getUUID();
		dro.bagentdata.vsessionid=bot.getSession();
		// object to return
		@Nonnull final DeRezObject_bObjectData drood=new DeRezObject_bObjectData();
		drood.vobjectlocalid=new U32(id);
		dro.bobjectdata=new ArrayList<>();
		dro.bobjectdata.add(drood);
		// derez config - type 9 is return to owner, 10 to return to 'last owner'.  9 seems to work :P
		dro.bagentblock.vdestination=new U8(9);
		//dro.bagentblock.vtransactionid=LLUUID.random();
		dro.bagentblock.vpacketcount=new U8(1);
		dro.bagentblock.vpacketnumber=new U8(1);
		bot.send(dro,true);
		log.info("Returned prim "+primuuid+"/"+localid+" from region "+region);
		return "0 - Returned object";
	}
	
	public enum CompressedFlags {
		NONE(0x00),
		SCRACHPAD(0x01),
		TREE(0x02),
		TEXT(0x04),
		PARTICLES(0x08),
		SOUND(0x10),
		PARENT(0x20),
		TEXTUREANIM(0x40),
		ANGULARVEL(0x80),
		NAMEVALUE(0x100),
		MEDIAURL(0x200);
		private final int id;
		
		CompressedFlags(final int id) {
			this.id=id;
		}
		
		// ---------- INSTANCE ----------
		public int getValue() {
			return id;
		}
	}
}
