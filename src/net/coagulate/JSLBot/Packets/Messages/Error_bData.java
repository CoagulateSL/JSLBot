package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class Error_bData extends Block {
	@Sequence(0)
	public S32 vcode=new S32();
	@Sequence(1)
	public Variable1 vtoken=new Variable1();
	@Sequence(2)
	public LLUUID vid=new LLUUID();
	@Sequence(3)
	public Variable1 vsystem=new Variable1();
	@Sequence(4)
	public Variable2 vmessage=new Variable2();
	@Sequence(5)
	public Variable2 vdata=new Variable2();
}
