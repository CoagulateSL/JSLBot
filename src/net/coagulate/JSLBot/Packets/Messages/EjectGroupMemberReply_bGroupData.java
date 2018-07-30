package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EjectGroupMemberReply_bGroupData extends Block {
	@Sequence(0)
	public LLUUID vgroupid=new LLUUID();
}
