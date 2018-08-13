package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RegionHandshakeReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 149; }
	public final String getName() { return "RegionHandshakeReply"; }
	@Sequence(0)
	public RegionHandshakeReply_bAgentData bagentdata=new RegionHandshakeReply_bAgentData();
	@Sequence(1)
	public RegionHandshakeReply_bRegionInfo bregioninfo=new RegionHandshakeReply_bRegionInfo();
	public RegionHandshakeReply(){}
	public RegionHandshakeReply(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
