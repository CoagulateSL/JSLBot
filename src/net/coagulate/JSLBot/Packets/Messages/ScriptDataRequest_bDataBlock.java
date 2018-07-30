package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptDataRequest_bDataBlock extends Block {
	@Sequence(0)
	public U64 vhash=new U64();
	@Sequence(1)
	public S8 vrequesttype=new S8();
	@Sequence(2)
	public Variable2 vrequest=new Variable2();
}
