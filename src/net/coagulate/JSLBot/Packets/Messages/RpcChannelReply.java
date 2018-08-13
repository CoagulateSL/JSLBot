package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RpcChannelReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 414; }
	public final String getName() { return "RpcChannelReply"; }
	@Sequence(0)
	public RpcChannelReply_bDataBlock bdatablock=new RpcChannelReply_bDataBlock();
}
