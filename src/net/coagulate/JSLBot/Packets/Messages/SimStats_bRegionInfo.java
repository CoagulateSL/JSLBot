package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimStats_bRegionInfo extends Block {
	@Sequence(0)
	public U64 vregionflagsextended=new U64();
}
