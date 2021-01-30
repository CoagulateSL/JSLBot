package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class InviteGroupResponse_bGroupData extends Block {
	@Sequence(0)
	public S32 vgrouplimit=new S32();
}
