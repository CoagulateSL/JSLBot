package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Stores data we know about an object.
 * Do not expect all data to be present unless you've requested it previously.
 *
 * @author Iain Price
 */

public class ObjectData {
	public final JSLBot bot;
	@Nonnull
	public U32 id;
	@Nullable
	public LLUUID fullid;
	@Nullable
	public U8 clickaction;
	@Nullable
	public LLVector3 scale;
	@Nullable
	public U32 parentid;
	@Nullable
	public String name;
	public boolean requested;
	@Nullable
	public String description;
	@Nullable
	public LLUUID owner;
	@Nullable
	public LLUUID group;
	@Nullable
	public LLUUID lastowner;
	public boolean agent;
	@Nonnull
	public String floattext="";
	public int crc;
	private float x=-1;
	private float y=-1;
	private float z=-1;

	public ObjectData(final JSLBot bot,
	                  final int id) {
		this.bot=bot;
		this.id=new U32(id);
	}

	public ObjectData(final JSLBot bot,
	                  @Nonnull final U32 id) {
		this.bot=bot;
		this.id=id;
	}

	// ---------- INSTANCE ----------
	@Nullable
	public String toString() {
		String r="";
		r+="#"+id.value;
		r+="@<"+x+","+y+","+z+"> ";
		if (clickaction!=null) { r+=" onClick:"+clickaction; }
		if (scale!=null) { r+=" Size:"+scale; }
		if (parentid!=null) { r+=" Parent:"+parentid.value; }
		if (name!=null) { r+=" '"+name+"'"; }
		if (description!=null) { r+=" ("+description+")"; }
		if (owner!=null) { r+=" Owner:"+owner.toUUIDString()+"="+bot.getUserName(owner); }
		if (group!=null) { r+=" Group:"+group.toUUIDString()+"="+bot.getUserName(group); }
		if (lastowner!=null) { r+=" LastOwner:"+lastowner.toUUIDString()+"="+bot.getUserName(lastowner); }
		return r;
	}

	public void position(final float newx,
	                     final float newy,
	                     final float newz) {
		//System.out.println(newx+","+newy+","+newz);
		x=newx;
		y=newy;
		z=newz;
		if (fullid!=null && bot!=null && bot.getUUID()!=null) {
			if (fullid.equals(bot.getUUID())) {
				bot.setPos(x,y,z);
			}
		}
	}
}
