package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RpcScriptRequestInboundForward extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 416; }
	public final String getName() { return "RpcScriptRequestInboundForward"; }
	@Sequence(0)
	public RpcScriptRequestInboundForward_bDataBlock bdatablock=new RpcScriptRequestInboundForward_bDataBlock();
}
