package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class GenericMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 261; }
	@Nonnull
    public final String getName() { return "GenericMessage"; }
	@Nonnull
    @Sequence(0)
	public GenericMessage_bAgentData bagentdata=new GenericMessage_bAgentData();
	@Nonnull
    @Sequence(1)
	public GenericMessage_bMethodData bmethoddata=new GenericMessage_bMethodData();
	@Sequence(2)
	public List<GenericMessage_bParamList> bparamlist;
	public GenericMessage(){}
	public GenericMessage(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
