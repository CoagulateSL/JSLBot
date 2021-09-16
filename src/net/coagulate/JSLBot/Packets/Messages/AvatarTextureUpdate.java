package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class AvatarTextureUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 4; }
	@Nonnull
    public final String getName() { return "AvatarTextureUpdate"; }
	@Nonnull
    @Sequence(0)
	public AvatarTextureUpdate_bAgentData bagentdata=new AvatarTextureUpdate_bAgentData();
	@Sequence(1)
	public List<AvatarTextureUpdate_bWearableData> bwearabledata;
	@Sequence(2)
	public List<AvatarTextureUpdate_bTextureData> btexturedata;
	public AvatarTextureUpdate(){}
	public AvatarTextureUpdate(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
