package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TeleportProgress extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 66; }
	public final String getName() { return "TeleportProgress"; }
	@Sequence(0)
	public TeleportProgress_bAgentData bagentdata=new TeleportProgress_bAgentData();
	@Sequence(1)
	public TeleportProgress_bInfo binfo=new TeleportProgress_bInfo();
	public TeleportProgress(){}
	public TeleportProgress(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
