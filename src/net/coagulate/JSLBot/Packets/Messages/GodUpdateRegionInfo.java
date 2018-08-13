package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GodUpdateRegionInfo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 143; }
	public final String getName() { return "GodUpdateRegionInfo"; }
	@Sequence(0)
	public GodUpdateRegionInfo_bAgentData bagentdata=new GodUpdateRegionInfo_bAgentData();
	@Sequence(1)
	public GodUpdateRegionInfo_bRegionInfo bregioninfo=new GodUpdateRegionInfo_bRegionInfo();
	public GodUpdateRegionInfo(){}
	public GodUpdateRegionInfo(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
