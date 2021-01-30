package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarAppearance extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 158; }
	public final String getName() { return "AvatarAppearance"; }
	@Sequence(0)
	public AvatarAppearance_bSender bsender=new AvatarAppearance_bSender();
	@Sequence(1)
	public AvatarAppearance_bObjectData bobjectdata=new AvatarAppearance_bObjectData();
	@Sequence(2)
	public List<AvatarAppearance_bVisualParam> bvisualparam;
	@Sequence(3)
	public List<AvatarAppearance_bAppearanceData> bappearancedata;
	@Sequence(4)
	public List<AvatarAppearance_bAppearanceHover> bappearancehover;
}
