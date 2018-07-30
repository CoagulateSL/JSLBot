package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RezScript extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 304; }
	public final String getName() { return "RezScript"; }
	@Sequence(0)
	public RezScript_bAgentData bagentdata=new RezScript_bAgentData();
	@Sequence(1)
	public RezScript_bUpdateBlock bupdateblock=new RezScript_bUpdateBlock();
	@Sequence(2)
	public RezScript_bInventoryBlock binventoryblock=new RezScript_bInventoryBlock();
}
