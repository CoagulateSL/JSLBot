package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ReplyTaskInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 290; }
	public final String getName() { return "ReplyTaskInventory"; }
	@Sequence(0)
	public ReplyTaskInventory_bInventoryData binventorydata=new ReplyTaskInventory_bInventoryData();
}
