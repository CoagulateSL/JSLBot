package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U8;

/** Stores data we know about an object.
 * Do not expect all data to be present unless you've requested it previously.
 * @author Iain Price
 */

public class ObjectData {
    public U32 id=null;
    public LLUUID fullid=null;
    public U8 clickaction=null;
    public LLVector3 scale=null;
    public U32 parentid=null;
    public String name=null;
    public boolean requested=false;
    public String description=null;
    public LLUUID owner=null;
    public LLUUID group=null;
    public LLUUID lastowner=null;
    public boolean agent=false;
    public String floattext="";
    public final JSLBot bot;
    private float x=-1;
    private float y=-1;
    private float z=-1;
    public int crc=0;
    public ObjectData(JSLBot bot,int id) { this.bot=bot; this.id=new U32(id); }
    public ObjectData(JSLBot bot,U32 id) { this.bot=bot; this.id=id; }
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
