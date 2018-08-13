package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CrossedRegion extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 7; }
	public final String getName() { return "CrossedRegion"; }
	@Sequence(0)
	public CrossedRegion_bAgentData bagentdata=new CrossedRegion_bAgentData();
	@Sequence(1)
	public CrossedRegion_bRegionData bregiondata=new CrossedRegion_bRegionData();
	@Sequence(2)
	public CrossedRegion_bInfo binfo=new CrossedRegion_bInfo();
	public CrossedRegion(){}
	public CrossedRegion(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
