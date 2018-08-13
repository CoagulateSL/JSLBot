package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AtomicPassObject extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 28; }
	public final String getName() { return "AtomicPassObject"; }
	@Sequence(0)
	public AtomicPassObject_bTaskData btaskdata=new AtomicPassObject_bTaskData();
}
