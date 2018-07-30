package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RpcScriptReplyInbound extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 417; }
	public final String getName() { return "RpcScriptReplyInbound"; }
	@Sequence(0)
	public RpcScriptReplyInbound_bDataBlock bdatablock=new RpcScriptReplyInbound_bDataBlock();
}
