package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class SetFollowCamProperties extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 159; }
	@Nonnull
    public final String getName() { return "SetFollowCamProperties"; }
	@Nonnull
    @Sequence(0)
	public SetFollowCamProperties_bObjectData bobjectdata=new SetFollowCamProperties_bObjectData();
	@Sequence(1)
	public List<SetFollowCamProperties_bCameraProperty> bcameraproperty;
}
