package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelClaim extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 209; }
	public final String getName() { return "ParcelClaim"; }
	@Sequence(0)
	public ParcelClaim_bAgentData bagentdata=new ParcelClaim_bAgentData();
	@Sequence(1)
	public ParcelClaim_bData bdata=new ParcelClaim_bData();
	@Sequence(2)
	public List<ParcelClaim_bParcelData> bparceldata;
	public ParcelClaim(){}
	public ParcelClaim(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
