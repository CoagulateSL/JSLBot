package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimulatorMapUpdate_bMapData extends Block {
	@Sequence(0)
	public U32 vflags=new U32();
}
