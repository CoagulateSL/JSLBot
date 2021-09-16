package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AvatarInterestsReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 172; }
	@Nonnull
    public final String getName() { return "AvatarInterestsReply"; }
	@Nonnull
    @Sequence(0)
	public AvatarInterestsReply_bAgentData bagentdata=new AvatarInterestsReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public AvatarInterestsReply_bPropertiesData bpropertiesdata=new AvatarInterestsReply_bPropertiesData();
	public AvatarInterestsReply(){}
	public AvatarInterestsReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
