package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MultipleObjectUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 2; }
	public final String getName() { return "MultipleObjectUpdate"; }
	@Sequence(0)
	public MultipleObjectUpdate_bAgentData bagentdata=new MultipleObjectUpdate_bAgentData();
	@Sequence(1)
	public List<MultipleObjectUpdate_bObjectData> bobjectdata;
	public MultipleObjectUpdate(){}
	public MultipleObjectUpdate(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
