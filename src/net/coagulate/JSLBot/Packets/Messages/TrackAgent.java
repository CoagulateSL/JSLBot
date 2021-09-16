package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class TrackAgent extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 130; }
	@Nonnull
    public final String getName() { return "TrackAgent"; }
	@Nonnull
    @Sequence(0)
	public TrackAgent_bAgentData bagentdata=new TrackAgent_bAgentData();
	@Nonnull
    @Sequence(1)
	public TrackAgent_bTargetData btargetdata=new TrackAgent_bTargetData();
	public TrackAgent(){}
	public TrackAgent(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
