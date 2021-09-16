package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ChangeInventoryItemFlags extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 271; }
	@Nonnull
    public final String getName() { return "ChangeInventoryItemFlags"; }
	@Nonnull
    @Sequence(0)
	public ChangeInventoryItemFlags_bAgentData bagentdata=new ChangeInventoryItemFlags_bAgentData();
	@Sequence(1)
	public List<ChangeInventoryItemFlags_bInventoryData> binventorydata;
	public ChangeInventoryItemFlags(){}
	public ChangeInventoryItemFlags(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
