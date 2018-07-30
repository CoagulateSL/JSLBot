package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class InternalScriptMail_bDataBlock extends Block {
	@Sequence(0)
	public Variable1 vfrom=new Variable1();
	@Sequence(1)
	public LLUUID vto=new LLUUID();
	@Sequence(2)
	public Variable1 vsubject=new Variable1();
	@Sequence(3)
	public Variable2 vbody=new Variable2();
}
