package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SystemMessage_bMethodData extends Block {
	@Sequence(0)
	public Variable1 vmethod=new Variable1();
	@Sequence(1)
	public LLUUID vinvoice=new LLUUID();
	@Sequence(2)
	public Fixed32 vdigest=new Fixed32();
}
