package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectUpdateCompressed_bObjectData extends Block {
	@Sequence(0)
	public U32 vupdateflags=new U32();
	@Sequence(1)
	public Variable2 vdata=new Variable2();
}
