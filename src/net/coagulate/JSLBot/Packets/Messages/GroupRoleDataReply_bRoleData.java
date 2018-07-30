package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupRoleDataReply_bRoleData extends Block {
	@Sequence(0)
	public LLUUID vroleid=new LLUUID();
	@Sequence(1)
	public Variable1 vname=new Variable1();
	@Sequence(2)
	public Variable1 vtitle=new Variable1();
	@Sequence(3)
	public Variable1 vdescription=new Variable1();
	@Sequence(4)
	public U64 vpowers=new U64();
	@Sequence(5)
	public U32 vmembers=new U32();
}
