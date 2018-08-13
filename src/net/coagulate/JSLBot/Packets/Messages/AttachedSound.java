package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AttachedSound extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 13; }
	public final String getName() { return "AttachedSound"; }
	@Sequence(0)
	public AttachedSound_bDataBlock bdatablock=new AttachedSound_bDataBlock();
}
