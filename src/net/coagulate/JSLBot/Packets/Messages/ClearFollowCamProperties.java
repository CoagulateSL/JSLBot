package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ClearFollowCamProperties extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 160; }
	@Nonnull
    public final String getName() { return "ClearFollowCamProperties"; }
	@Nonnull
    @Sequence(0)
	public ClearFollowCamProperties_bObjectData bobjectdata=new ClearFollowCamProperties_bObjectData();
}
