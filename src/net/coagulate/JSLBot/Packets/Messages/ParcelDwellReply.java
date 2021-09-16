package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelDwellReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 219; }
	@Nonnull
    public final String getName() { return "ParcelDwellReply"; }
	@Nonnull
    @Sequence(0)
	public ParcelDwellReply_bAgentData bagentdata=new ParcelDwellReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelDwellReply_bData bdata=new ParcelDwellReply_bData();
	public ParcelDwellReply(){}
	public ParcelDwellReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
