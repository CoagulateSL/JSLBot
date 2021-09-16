package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelInfoReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 55; }
	@Nonnull
    public final String getName() { return "ParcelInfoReply"; }
	@Nonnull
    @Sequence(0)
	public ParcelInfoReply_bAgentData bagentdata=new ParcelInfoReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelInfoReply_bData bdata=new ParcelInfoReply_bData();
	public ParcelInfoReply(){}
	public ParcelInfoReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
