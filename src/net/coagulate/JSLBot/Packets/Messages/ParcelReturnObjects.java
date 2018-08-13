package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelReturnObjects extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 199; }
	public final String getName() { return "ParcelReturnObjects"; }
	@Sequence(0)
	public ParcelReturnObjects_bAgentData bagentdata=new ParcelReturnObjects_bAgentData();
	@Sequence(1)
	public ParcelReturnObjects_bParcelData bparceldata=new ParcelReturnObjects_bParcelData();
	@Sequence(2)
	public List<ParcelReturnObjects_bTaskIDs> btaskids;
	@Sequence(3)
	public List<ParcelReturnObjects_bOwnerIDs> bownerids;
	public ParcelReturnObjects(){}
	public ParcelReturnObjects(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
