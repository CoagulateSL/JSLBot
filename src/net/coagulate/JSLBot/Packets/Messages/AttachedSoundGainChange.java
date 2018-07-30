package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AttachedSoundGainChange extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 14; }
	public final String getName() { return "AttachedSoundGainChange"; }
	@Sequence(0)
	public AttachedSoundGainChange_bDataBlock bdatablock=new AttachedSoundGainChange_bDataBlock();
}
