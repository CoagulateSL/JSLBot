package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class MoveInventoryFolder extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 275; }
	@Nonnull
    public final String getName() { return "MoveInventoryFolder"; }
	@Nonnull
    @Sequence(0)
	public MoveInventoryFolder_bAgentData bagentdata=new MoveInventoryFolder_bAgentData();
	@Sequence(1)
	public List<MoveInventoryFolder_bInventoryData> binventorydata;
	public MoveInventoryFolder(){}
	public MoveInventoryFolder(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
