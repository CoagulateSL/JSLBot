package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarAnimation_bAnimationList extends Block {
	@Sequence(0)
	public LLUUID vanimid=new LLUUID();
	@Sequence(1)
	public S32 vanimsequenceid=new S32();
}
