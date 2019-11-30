package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/** Stores data we know about an object.
 * Do not expect all data to be present unless you've requested it previously.
 * @author Iain Price
 */

public class ObjectData {
    @Nullable
    public U32 id=null;
    @Nullable
    public LLUUID fullid=null;
    @Nullable
    public U8 clickaction=null;
    @Nullable
    public LLVector3 scale=null;
    @Nullable
    public U32 parentid=null;
    @Nullable
    public String name=null;
    public boolean requested=false;
    @Nullable
    public String description=null;
    @Nullable
    public LLUUID owner=null;
    @Nullable
    public LLUUID group=null;
    @Nullable
    public LLUUID lastowner=null;
    public boolean agent=false;
    @Nonnull
    public String floattext="";
    public final JSLBot bot;
    private float x=-1;
    private float y=-1;
    private float z=-1;
    public int crc=0;
    public ObjectData(JSLBot bot,int id) { this.bot=bot; this.id=new U32(id); }
    public ObjectData(JSLBot bot, @Nullable U32 id) { this.bot=bot; this.id=id; }
    @Nullable
    public String toString() {
        String r="";
        if (id!=null) { r+="#"+id.value; } else { r+="id??"; }
        r+="@<"+x+","+y+","+z+"> ";
        if (clickaction!=null) { r+=" onClick:"+clickaction; }
        if (scale!=null) { r+=" Size:"+scale.toString(); }
        if (parentid!=null) { r+=" Parent:"+parentid.value; }
        if (name!=null) { r+=" '"+name+"'"; }
        if (description!=null) { r+=" ("+description+")"; }
        if (owner!=null) { r+= " Owner:"+owner.toUUIDString()+"="+bot.getUserName(owner); }
        if (group!=null) { r+= " Group:"+group.toUUIDString()+"="+bot.getUserName(group); }
        if (lastowner!=null) { r+= " LastOwner:"+lastowner.toUUIDString()+"="+bot.getUserName(lastowner); }
        return r;
    }

    public void position(float newx,float newy,float newz) {
        //System.out.println(newx+","+newy+","+newz);
        x=newx; y=newy; z=newz;
        if (fullid!=null && bot!=null && bot.getUUID()!=null) {
            if (fullid.equals(bot.getUUID())) {
                bot.setPos(x,y,z);
            }
        }
    }
}
