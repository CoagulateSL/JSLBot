package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RemoveMuteListEntry extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 264; }
	public final String getName() { return "RemoveMuteListEntry"; }
	@Sequence(0)
	public RemoveMuteListEntry_bAgentData bagentdata=new RemoveMuteListEntry_bAgentData();
	@Sequence(1)
	public RemoveMuteListEntry_bMuteData bmutedata=new RemoveMuteListEntry_bMuteData();
	public RemoveMuteListEntry(){}
	public RemoveMuteListEntry(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
