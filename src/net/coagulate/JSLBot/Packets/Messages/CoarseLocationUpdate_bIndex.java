package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CoarseLocationUpdate_bIndex extends Block {
	@Sequence(0)
	public S16 vyou=new S16();
	@Sequence(1)
	public S16 vprey=new S16();
}
