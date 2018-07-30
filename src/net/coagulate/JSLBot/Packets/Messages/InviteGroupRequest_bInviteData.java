package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class InviteGroupRequest_bInviteData extends Block {
	@Sequence(0)
	public LLUUID vinviteeid=new LLUUID();
	@Sequence(1)
	public LLUUID vroleid=new LLUUID();
}
