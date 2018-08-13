package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DeRezAck extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 292; }
	public final String getName() { return "DeRezAck"; }
	@Sequence(0)
	public DeRezAck_bTransactionData btransactiondata=new DeRezAck_bTransactionData();
}
