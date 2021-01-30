package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimStatus_bSimFlags extends Block {
	@Sequence(0)
	public U64 vflags=new U64();
}
