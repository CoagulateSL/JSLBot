package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class AvatarAppearance extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 158; }
	@Nonnull
    public final String getName() { return "AvatarAppearance"; }
	@Nonnull
    @Sequence(0)
	public AvatarAppearance_bSender bsender=new AvatarAppearance_bSender();
	@Nonnull
    @Sequence(1)
	public AvatarAppearance_bObjectData bobjectdata=new AvatarAppearance_bObjectData();
	@Sequence(2)
	public List<AvatarAppearance_bVisualParam> bvisualparam;
	@Sequence(3)
	public List<AvatarAppearance_bAppearanceData> bappearancedata;
	@Sequence(4)
	public List<AvatarAppearance_bAppearanceHover> bappearancehover;
}
