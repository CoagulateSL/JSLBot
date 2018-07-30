package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LogParcelChanges_bRegionData extends Block {
	@Sequence(0)
	public U64 vregionhandle=new U64();
}
