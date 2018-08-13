package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarAnimation extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 20; }
	public final String getName() { return "AvatarAnimation"; }
	@Sequence(0)
	public AvatarAnimation_bSender bsender=new AvatarAnimation_bSender();
	@Sequence(1)
	public List<AvatarAnimation_bAnimationList> banimationlist;
	@Sequence(2)
	public List<AvatarAnimation_bAnimationSourceList> banimationsourcelist;
	@Sequence(3)
	public List<AvatarAnimation_bPhysicalAvatarEventList> bphysicalavatareventlist;
}
