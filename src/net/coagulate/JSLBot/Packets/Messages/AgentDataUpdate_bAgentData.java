package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentDataUpdate_bAgentData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public Variable1 vfirstname=new Variable1();
	@Sequence(2)
	public Variable1 vlastname=new Variable1();
	@Sequence(3)
	public Variable1 vgrouptitle=new Variable1();
	@Sequence(4)
	public LLUUID vactivegroupid=new LLUUID();
	@Sequence(5)
	public U64 vgrouppowers=new U64();
	@Sequence(6)
	public Variable1 vgroupname=new Variable1();
}
