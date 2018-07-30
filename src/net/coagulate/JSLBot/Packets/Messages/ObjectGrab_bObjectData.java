package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectGrab_bObjectData extends Block {
	@Sequence(0)
	public U32 vlocalid=new U32();
	@Sequence(1)
	public LLVector3 vgraboffset=new LLVector3();
}
