package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class KickUser extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 163; }
	public final String getName() { return "KickUser"; }
	@Sequence(0)
	public KickUser_bTargetBlock btargetblock=new KickUser_bTargetBlock();
	@Sequence(1)
	public KickUser_bUserInfo buserinfo=new KickUser_bUserInfo();
}
