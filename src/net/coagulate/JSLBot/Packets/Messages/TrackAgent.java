package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TrackAgent extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 130; }
	public final String getName() { return "TrackAgent"; }
	@Sequence(0)
	public TrackAgent_bAgentData bagentdata=new TrackAgent_bAgentData();
	@Sequence(1)
	public TrackAgent_bTargetData btargetdata=new TrackAgent_bTargetData();
	public TrackAgent(){}
	public TrackAgent(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
