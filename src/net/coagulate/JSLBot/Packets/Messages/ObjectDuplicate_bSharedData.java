package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectDuplicate_bSharedData extends Block {
	@Sequence(0)
	public LLVector3 voffset=new LLVector3();
	@Sequence(1)
	public U32 vduplicateflags=new U32();
}
