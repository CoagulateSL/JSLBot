package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TransferAbort extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 155; }
	public final String getName() { return "TransferAbort"; }
	@Sequence(0)
	public TransferAbort_bTransferInfo btransferinfo=new TransferAbort_bTransferInfo();
}
