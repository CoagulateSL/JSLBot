package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class Redo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 76; }
	public final String getName() { return "Redo"; }
	@Sequence(0)
	public Redo_bAgentData bagentdata=new Redo_bAgentData();
	@Sequence(1)
	public List<Redo_bObjectData> bobjectdata;
	public Redo(){}
	public Redo(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
