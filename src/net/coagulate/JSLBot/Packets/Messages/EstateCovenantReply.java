package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EstateCovenantReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 204; }
	public final String getName() { return "EstateCovenantReply"; }
	@Sequence(0)
	public EstateCovenantReply_bData bdata=new EstateCovenantReply_bData();
}
