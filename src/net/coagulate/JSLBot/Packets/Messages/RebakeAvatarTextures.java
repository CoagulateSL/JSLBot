package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RebakeAvatarTextures extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 87; }
	@Nonnull
    public final String getName() { return "RebakeAvatarTextures"; }
	@Nonnull
    @Sequence(0)
	public RebakeAvatarTextures_bTextureData btexturedata=new RebakeAvatarTextures_bTextureData();
}
