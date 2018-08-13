package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class StateSave extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 127; }
	public final String getName() { return "StateSave"; }
	@Sequence(0)
	public StateSave_bAgentData bagentdata=new StateSave_bAgentData();
	@Sequence(1)
	public StateSave_bDataBlock bdatablock=new StateSave_bDataBlock();
	public StateSave(){}
	public StateSave(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
