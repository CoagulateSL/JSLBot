package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TransferRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 153; }
	public final String getName() { return "TransferRequest"; }
	@Sequence(0)
	public TransferRequest_bTransferInfo btransferinfo=new TransferRequest_bTransferInfo();
}
