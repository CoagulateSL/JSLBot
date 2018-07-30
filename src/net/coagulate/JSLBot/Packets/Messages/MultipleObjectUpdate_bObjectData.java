package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MultipleObjectUpdate_bObjectData extends Block {
	@Sequence(0)
	public U32 vobjectlocalid=new U32();
	@Sequence(1)
	public U8 vtype=new U8();
	@Sequence(2)
	public Variable1 vdata=new Variable1();
}
