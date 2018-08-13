package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelReclaim extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 208; }
	public final String getName() { return "ParcelReclaim"; }
	@Sequence(0)
	public ParcelReclaim_bAgentData bagentdata=new ParcelReclaim_bAgentData();
	@Sequence(1)
	public ParcelReclaim_bData bdata=new ParcelReclaim_bData();
	public ParcelReclaim(){}
	public ParcelReclaim(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
