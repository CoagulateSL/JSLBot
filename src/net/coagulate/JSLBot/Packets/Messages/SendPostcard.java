package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class SendPostcard extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 412; }
	@Nonnull
    public final String getName() { return "SendPostcard"; }
	@Nonnull
    @Sequence(0)
	public SendPostcard_bAgentData bagentdata=new SendPostcard_bAgentData();
	public SendPostcard(){}
	public SendPostcard(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
