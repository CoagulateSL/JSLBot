package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RegionHandshake_bRegionInfo4 extends Block {
	@Sequence(0)
	public U64 vregionflagsextended=new U64();
	@Sequence(1)
	public U64 vregionprotocols=new U64();
}
