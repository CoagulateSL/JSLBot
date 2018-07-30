package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GodKickUser extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 165; }
	public final String getName() { return "GodKickUser"; }
	@Sequence(0)
	public GodKickUser_bUserInfo buserinfo=new GodKickUser_bUserInfo();
}
