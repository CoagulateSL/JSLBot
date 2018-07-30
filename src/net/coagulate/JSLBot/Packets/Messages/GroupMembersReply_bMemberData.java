package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupMembersReply_bMemberData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public S32 vcontribution=new S32();
	@Sequence(2)
	public Variable1 vonlinestatus=new Variable1();
	@Sequence(3)
	public U64 vagentpowers=new U64();
	@Sequence(4)
	public Variable1 vtitle=new Variable1();
	@Sequence(5)
	public BOOL visowner=new BOOL();
}
