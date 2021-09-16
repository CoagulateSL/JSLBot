package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class GodlikeMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 259; }
	@Nonnull
    public final String getName() { return "GodlikeMessage"; }
	@Nonnull
    @Sequence(0)
	public GodlikeMessage_bAgentData bagentdata=new GodlikeMessage_bAgentData();
	@Nonnull
    @Sequence(1)
	public GodlikeMessage_bMethodData bmethoddata=new GodlikeMessage_bMethodData();
	@Sequence(2)
	public List<GodlikeMessage_bParamList> bparamlist;
	public GodlikeMessage(){}
	public GodlikeMessage(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
