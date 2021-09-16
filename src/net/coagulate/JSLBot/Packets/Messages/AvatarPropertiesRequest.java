package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AvatarPropertiesRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 169; }
	@Nonnull
    public final String getName() { return "AvatarPropertiesRequest"; }
	@Nonnull
    @Sequence(0)
	public AvatarPropertiesRequest_bAgentData bagentdata=new AvatarPropertiesRequest_bAgentData();
	public AvatarPropertiesRequest(){}
	public AvatarPropertiesRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
