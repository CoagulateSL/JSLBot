package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RpcScriptRequestInbound extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 415; }
	public final String getName() { return "RpcScriptRequestInbound"; }
	@Sequence(0)
	public RpcScriptRequestInbound_bTargetBlock btargetblock=new RpcScriptRequestInbound_bTargetBlock();
	@Sequence(1)
	public RpcScriptRequestInbound_bDataBlock bdatablock=new RpcScriptRequestInbound_bDataBlock();
}
