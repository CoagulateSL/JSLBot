package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class InternalScriptMail extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 16; }
	public final String getName() { return "InternalScriptMail"; }
	@Sequence(0)
	public InternalScriptMail_bDataBlock bdatablock=new InternalScriptMail_bDataBlock();
}
