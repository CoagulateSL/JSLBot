package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AvatarPropertiesRequestBackend extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 170; }
	@Nonnull
    public final String getName() { return "AvatarPropertiesRequestBackend"; }
	@Nonnull
    @Sequence(0)
	public AvatarPropertiesRequestBackend_bAgentData bagentdata=new AvatarPropertiesRequestBackend_bAgentData();
	public AvatarPropertiesRequestBackend(){}
	public AvatarPropertiesRequestBackend(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
