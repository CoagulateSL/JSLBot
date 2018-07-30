package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CoarseLocationUpdate_bLocation extends Block {
	@Sequence(0)
	public U8 vx=new U8();
	@Sequence(1)
	public U8 vy=new U8();
	@Sequence(2)
	public U8 vz=new U8();
}
