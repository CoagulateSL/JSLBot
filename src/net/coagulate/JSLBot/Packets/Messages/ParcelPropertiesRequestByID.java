package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelPropertiesRequestByID extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 197; }
	public final String getName() { return "ParcelPropertiesRequestByID"; }
	@Sequence(0)
	public ParcelPropertiesRequestByID_bAgentData bagentdata=new ParcelPropertiesRequestByID_bAgentData();
	@Sequence(1)
	public ParcelPropertiesRequestByID_bParcelData bparceldata=new ParcelPropertiesRequestByID_bParcelData();
	public ParcelPropertiesRequestByID(){}
	public ParcelPropertiesRequestByID(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
