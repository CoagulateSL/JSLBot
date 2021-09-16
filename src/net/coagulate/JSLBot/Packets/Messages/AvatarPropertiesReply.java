package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AvatarPropertiesReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 171; }
	@Nonnull
    public final String getName() { return "AvatarPropertiesReply"; }
	@Nonnull
    @Sequence(0)
	public AvatarPropertiesReply_bAgentData bagentdata=new AvatarPropertiesReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public AvatarPropertiesReply_bPropertiesData bpropertiesdata=new AvatarPropertiesReply_bPropertiesData();
	public AvatarPropertiesReply(){}
	public AvatarPropertiesReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
