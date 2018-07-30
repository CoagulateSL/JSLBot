package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class PreloadSound extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 15; }
	public final String getName() { return "PreloadSound"; }
	@Sequence(0)
	public List<PreloadSound_bDataBlock> bdatablock;
}
