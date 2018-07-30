package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectUpdate_bRegionData extends Block {
	@Sequence(0)
	public U64 vregionhandle=new U64();
	@Sequence(1)
	public U16 vtimedilation=new U16();
}
