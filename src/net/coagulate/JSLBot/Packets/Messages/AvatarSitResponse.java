package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarSitResponse extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 21; }
	public final String getName() { return "AvatarSitResponse"; }
	@Sequence(0)
	public AvatarSitResponse_bSitObject bsitobject=new AvatarSitResponse_bSitObject();
	@Sequence(1)
	public AvatarSitResponse_bSitTransform bsittransform=new AvatarSitResponse_bSitTransform();
}
