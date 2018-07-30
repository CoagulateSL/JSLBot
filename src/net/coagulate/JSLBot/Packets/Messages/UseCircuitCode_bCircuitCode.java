package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UseCircuitCode_bCircuitCode extends Block {
	@Sequence(0)
	public U32 vcode=new U32();
	@Sequence(1)
	public LLUUID vsessionid=new LLUUID();
	@Sequence(2)
	public LLUUID vid=new LLUUID();
}
