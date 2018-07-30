package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AbortXfer extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 157; }
	public final String getName() { return "AbortXfer"; }
	@Sequence(0)
	public AbortXfer_bXferID bxferid=new AbortXfer_bXferID();
}
