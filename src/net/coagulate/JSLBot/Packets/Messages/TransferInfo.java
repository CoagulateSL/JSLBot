package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TransferInfo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 154; }
	public final String getName() { return "TransferInfo"; }
	@Sequence(0)
	public TransferInfo_bTransferInfo btransferinfo=new TransferInfo_bTransferInfo();
}
