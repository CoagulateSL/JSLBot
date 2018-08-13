package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TransferPacket extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 17; }
	public final String getName() { return "TransferPacket"; }
	@Sequence(0)
	public TransferPacket_bTransferData btransferdata=new TransferPacket_bTransferData();
}
