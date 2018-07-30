package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectIncludeInSearch_bObjectData extends Block {
	@Sequence(0)
	public U32 vobjectlocalid=new U32();
	@Sequence(1)
	public BOOL vincludeinsearch=new BOOL();
}
