package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SoundTrigger extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 29; }
	public final String getName() { return "SoundTrigger"; }
	@Sequence(0)
	public SoundTrigger_bSoundData bsounddata=new SoundTrigger_bSoundData();
}
