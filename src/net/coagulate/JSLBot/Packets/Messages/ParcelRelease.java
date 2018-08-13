package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelRelease extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 212; }
	public final String getName() { return "ParcelRelease"; }
	@Sequence(0)
	public ParcelRelease_bAgentData bagentdata=new ParcelRelease_bAgentData();
	@Sequence(1)
	public ParcelRelease_bData bdata=new ParcelRelease_bData();
	public ParcelRelease(){}
	public ParcelRelease(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
