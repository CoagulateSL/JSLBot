package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarPicksReply_bData extends Block {
	@Sequence(0)
	public LLUUID vpickid=new LLUUID();
	@Sequence(1)
	public Variable1 vpickname=new Variable1();
}
