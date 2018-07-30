package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class KillChildAgents extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 242; }
	public final String getName() { return "KillChildAgents"; }
	@Sequence(0)
	public KillChildAgents_bIDBlock bidblock=new KillChildAgents_bIDBlock();
}
