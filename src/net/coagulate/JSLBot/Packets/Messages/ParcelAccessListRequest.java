package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelAccessListRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 215; }
	public final String getName() { return "ParcelAccessListRequest"; }
	@Sequence(0)
	public ParcelAccessListRequest_bAgentData bagentdata=new ParcelAccessListRequest_bAgentData();
	@Sequence(1)
	public ParcelAccessListRequest_bData bdata=new ParcelAccessListRequest_bData();
	public ParcelAccessListRequest(){}
	public ParcelAccessListRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
