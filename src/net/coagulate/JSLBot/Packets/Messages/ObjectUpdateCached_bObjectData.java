package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectUpdateCached_bObjectData extends Block {
	@Sequence(0)
	public U32 vid=new U32();
	@Sequence(1)
	public U32 vcrc=new U32();
	@Sequence(2)
	public U32 vupdateflags=new U32();
}
