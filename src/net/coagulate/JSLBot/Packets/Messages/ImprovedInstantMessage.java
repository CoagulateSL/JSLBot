package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ImprovedInstantMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 254; }
	@Nonnull
    public final String getName() { return "ImprovedInstantMessage"; }
	@Nonnull
    @Sequence(0)
	public ImprovedInstantMessage_bAgentData bagentdata=new ImprovedInstantMessage_bAgentData();
	@Nonnull
    @Sequence(1)
	public ImprovedInstantMessage_bMessageBlock bmessageblock=new ImprovedInstantMessage_bMessageBlock();
	@Nonnull
    @Sequence(2)
	public ImprovedInstantMessage_bEstateBlock bestateblock=new ImprovedInstantMessage_bEstateBlock();
	public ImprovedInstantMessage(){}
	public ImprovedInstantMessage(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
