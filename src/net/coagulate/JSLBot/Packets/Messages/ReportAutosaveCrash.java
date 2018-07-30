package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ReportAutosaveCrash extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 128; }
	public final String getName() { return "ReportAutosaveCrash"; }
	@Sequence(0)
	public ReportAutosaveCrash_bAutosaveData bautosavedata=new ReportAutosaveCrash_bAutosaveData();
}
