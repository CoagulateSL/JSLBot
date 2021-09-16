package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class Error extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 423; }
	@Nonnull
    public final String getName() { return "Error"; }
	@Nonnull
    @Sequence(0)
	public Error_bAgentData bagentdata=new Error_bAgentData();
	@Nonnull
    @Sequence(1)
	public Error_bData bdata=new Error_bData();
	public Error(){}
	public Error(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
