package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelObjectOwnersRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 56; }
	public final String getName() { return "ParcelObjectOwnersRequest"; }
	@Sequence(0)
	public ParcelObjectOwnersRequest_bAgentData bagentdata=new ParcelObjectOwnersRequest_bAgentData();
	@Sequence(1)
	public ParcelObjectOwnersRequest_bParcelData bparceldata=new ParcelObjectOwnersRequest_bParcelData();
	public ParcelObjectOwnersRequest(){}
	public ParcelObjectOwnersRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
