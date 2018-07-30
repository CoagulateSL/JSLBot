package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimulatorSetMap_bMapData extends Block {
	@Sequence(0)
	public U64 vregionhandle=new U64();
	@Sequence(1)
	public S32 vtype=new S32();
	@Sequence(2)
	public LLUUID vmapimage=new LLUUID();
}
