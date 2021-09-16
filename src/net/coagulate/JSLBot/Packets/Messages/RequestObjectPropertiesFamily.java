package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RequestObjectPropertiesFamily extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 5; }
	@Nonnull
    public final String getName() { return "RequestObjectPropertiesFamily"; }
	@Nonnull
    @Sequence(0)
	public RequestObjectPropertiesFamily_bAgentData bagentdata=new RequestObjectPropertiesFamily_bAgentData();
	@Nonnull
    @Sequence(1)
	public RequestObjectPropertiesFamily_bObjectData bobjectdata=new RequestObjectPropertiesFamily_bObjectData();
	public RequestObjectPropertiesFamily(){}
	public RequestObjectPropertiesFamily(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
