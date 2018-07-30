package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SetSimPresenceInDatabase_bSimData extends Block {
	@Sequence(0)
	public LLUUID vregionid=new LLUUID();
	@Sequence(1)
	public Variable1 vhostname=new Variable1();
	@Sequence(2)
	public U32 vgridx=new U32();
	@Sequence(3)
	public U32 vgridy=new U32();
	@Sequence(4)
	public S32 vpid=new S32();
	@Sequence(5)
	public S32 vagentcount=new S32();
	@Sequence(6)
	public S32 vtimetolive=new S32();
	@Sequence(7)
	public Variable1 vstatus=new Variable1();
}
