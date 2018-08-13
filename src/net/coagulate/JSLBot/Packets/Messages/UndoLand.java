package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UndoLand extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 77; }
	public final String getName() { return "UndoLand"; }
	@Sequence(0)
	public UndoLand_bAgentData bagentdata=new UndoLand_bAgentData();
	public UndoLand(){}
	public UndoLand(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
