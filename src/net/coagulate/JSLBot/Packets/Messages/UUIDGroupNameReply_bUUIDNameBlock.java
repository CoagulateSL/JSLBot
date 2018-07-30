package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UUIDGroupNameReply_bUUIDNameBlock extends Block {
	@Sequence(0)
	public LLUUID vid=new LLUUID();
	@Sequence(1)
	public Variable1 vgroupname=new Variable1();
}
