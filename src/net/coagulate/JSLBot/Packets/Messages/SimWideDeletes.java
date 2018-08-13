package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimWideDeletes extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 129; }
	public final String getName() { return "SimWideDeletes"; }
	@Sequence(0)
	public SimWideDeletes_bAgentData bagentdata=new SimWideDeletes_bAgentData();
	@Sequence(1)
	public SimWideDeletes_bDataBlock bdatablock=new SimWideDeletes_bDataBlock();
	public SimWideDeletes(){}
	public SimWideDeletes(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
