package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EconomyData extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 25; }
	public final String getName() { return "EconomyData"; }
	@Sequence(0)
	public EconomyData_bInfo binfo=new EconomyData_bInfo();
}
