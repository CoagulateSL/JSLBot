package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelGodForceOwner extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 214; }
	public final String getName() { return "ParcelGodForceOwner"; }
	@Sequence(0)
	public ParcelGodForceOwner_bAgentData bagentdata=new ParcelGodForceOwner_bAgentData();
	@Sequence(1)
	public ParcelGodForceOwner_bData bdata=new ParcelGodForceOwner_bData();
}
