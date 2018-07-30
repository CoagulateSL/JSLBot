package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class StartLure extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 70; }
	public final String getName() { return "StartLure"; }
	@Sequence(0)
	public StartLure_bAgentData bagentdata=new StartLure_bAgentData();
	@Sequence(1)
	public StartLure_bInfo binfo=new StartLure_bInfo();
	@Sequence(2)
	public List<StartLure_bTargetData> btargetdata;
}
