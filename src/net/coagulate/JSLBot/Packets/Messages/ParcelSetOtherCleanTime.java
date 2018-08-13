package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelSetOtherCleanTime extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 200; }
	public final String getName() { return "ParcelSetOtherCleanTime"; }
	@Sequence(0)
	public ParcelSetOtherCleanTime_bAgentData bagentdata=new ParcelSetOtherCleanTime_bAgentData();
	@Sequence(1)
	public ParcelSetOtherCleanTime_bParcelData bparceldata=new ParcelSetOtherCleanTime_bParcelData();
	public ParcelSetOtherCleanTime(){}
	public ParcelSetOtherCleanTime(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
