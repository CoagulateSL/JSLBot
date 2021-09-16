package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class EstateOwnerMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 260; }
	@Nonnull
    public final String getName() { return "EstateOwnerMessage"; }
	@Nonnull
    @Sequence(0)
	public EstateOwnerMessage_bAgentData bagentdata=new EstateOwnerMessage_bAgentData();
	@Nonnull
    @Sequence(1)
	public EstateOwnerMessage_bMethodData bmethoddata=new EstateOwnerMessage_bMethodData();
	@Sequence(2)
	public List<EstateOwnerMessage_bParamList> bparamlist;
	public EstateOwnerMessage(){}
	public EstateOwnerMessage(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
