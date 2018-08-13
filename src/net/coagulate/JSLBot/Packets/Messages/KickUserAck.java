package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class KickUserAck extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 164; }
	public final String getName() { return "KickUserAck"; }
	@Sequence(0)
	public KickUserAck_bUserInfo buserinfo=new KickUserAck_bUserInfo();
}
