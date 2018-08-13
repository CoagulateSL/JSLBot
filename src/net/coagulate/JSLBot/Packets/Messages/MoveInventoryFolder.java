package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MoveInventoryFolder extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 275; }
	public final String getName() { return "MoveInventoryFolder"; }
	@Sequence(0)
	public MoveInventoryFolder_bAgentData bagentdata=new MoveInventoryFolder_bAgentData();
	@Sequence(1)
	public List<MoveInventoryFolder_bInventoryData> binventorydata;
	public MoveInventoryFolder(){}
	public MoveInventoryFolder(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
