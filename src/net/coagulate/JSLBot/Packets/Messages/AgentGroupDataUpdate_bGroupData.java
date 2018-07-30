package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentGroupDataUpdate_bGroupData extends Block {
	@Sequence(0)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(1)
	public U64 vgrouppowers=new U64();
	@Sequence(2)
	public BOOL vacceptnotices=new BOOL();
	@Sequence(3)
	public LLUUID vgroupinsigniaid=new LLUUID();
	@Sequence(4)
	public S32 vcontribution=new S32();
	@Sequence(5)
	public Variable1 vgroupname=new Variable1();
}
