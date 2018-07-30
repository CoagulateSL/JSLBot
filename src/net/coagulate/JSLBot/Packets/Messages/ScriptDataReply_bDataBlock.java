package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptDataReply_bDataBlock extends Block {
	@Sequence(0)
	public U64 vhash=new U64();
	@Sequence(1)
	public Variable2 vreply=new Variable2();
}
