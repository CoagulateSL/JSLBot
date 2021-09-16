package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AvatarPropertiesUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 174; }
	@Nonnull
    public final String getName() { return "AvatarPropertiesUpdate"; }
	@Nonnull
    @Sequence(0)
	public AvatarPropertiesUpdate_bAgentData bagentdata=new AvatarPropertiesUpdate_bAgentData();
	@Nonnull
    @Sequence(1)
	public AvatarPropertiesUpdate_bPropertiesData bpropertiesdata=new AvatarPropertiesUpdate_bPropertiesData();
	public AvatarPropertiesUpdate(){}
	public AvatarPropertiesUpdate(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
