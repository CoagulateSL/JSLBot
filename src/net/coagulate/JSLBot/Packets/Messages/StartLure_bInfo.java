package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class StartLure_bInfo extends Block {
	@Sequence(0)
	public U8 vluretype=new U8();
	@Sequence(1)
	public Variable1 vmessage=new Variable1();
}
