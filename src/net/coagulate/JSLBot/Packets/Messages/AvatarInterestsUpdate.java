package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AvatarInterestsUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 175; }
	@Nonnull
    public final String getName() { return "AvatarInterestsUpdate"; }
	@Nonnull
    @Sequence(0)
	public AvatarInterestsUpdate_bAgentData bagentdata=new AvatarInterestsUpdate_bAgentData();
	@Nonnull
    @Sequence(1)
	public AvatarInterestsUpdate_bPropertiesData bpropertiesdata=new AvatarInterestsUpdate_bPropertiesData();
	public AvatarInterestsUpdate(){}
	public AvatarInterestsUpdate(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
