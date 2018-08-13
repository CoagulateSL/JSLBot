package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RegionInfo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 142; }
	public final String getName() { return "RegionInfo"; }
	@Sequence(0)
	public RegionInfo_bAgentData bagentdata=new RegionInfo_bAgentData();
	@Sequence(1)
	public RegionInfo_bRegionInfo bregioninfo=new RegionInfo_bRegionInfo();
	@Sequence(2)
	public RegionInfo_bRegionInfo2 bregioninfo2=new RegionInfo_bRegionInfo2();
	public RegionInfo(){}
	public RegionInfo(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
