package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelJoin extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 210; }
	public final String getName() { return "ParcelJoin"; }
	@Sequence(0)
	public ParcelJoin_bAgentData bagentdata=new ParcelJoin_bAgentData();
	@Sequence(1)
	public ParcelJoin_bParcelData bparceldata=new ParcelJoin_bParcelData();
	public ParcelJoin(){}
	public ParcelJoin(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
