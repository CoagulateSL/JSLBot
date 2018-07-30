package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectDescription_bObjectData extends Block {
	@Sequence(0)
	public U32 vlocalid=new U32();
	@Sequence(1)
	public Variable1 vdescription=new Variable1();
}
