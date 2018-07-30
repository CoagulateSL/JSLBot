package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupDataUpdate_bAgentGroupData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(2)
	public U64 vagentpowers=new U64();
	@Sequence(3)
	public Variable1 vgrouptitle=new Variable1();
}
