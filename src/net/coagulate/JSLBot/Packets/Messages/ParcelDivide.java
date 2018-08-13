package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelDivide extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 211; }
	public final String getName() { return "ParcelDivide"; }
	@Sequence(0)
	public ParcelDivide_bAgentData bagentdata=new ParcelDivide_bAgentData();
	@Sequence(1)
	public ParcelDivide_bParcelData bparceldata=new ParcelDivide_bParcelData();
	public ParcelDivide(){}
	public ParcelDivide(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
