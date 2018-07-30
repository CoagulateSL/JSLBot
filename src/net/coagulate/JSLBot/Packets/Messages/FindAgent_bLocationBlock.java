package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class FindAgent_bLocationBlock extends Block {
	@Sequence(0)
	public F64 vglobalx=new F64();
	@Sequence(1)
	public F64 vglobaly=new F64();
}
