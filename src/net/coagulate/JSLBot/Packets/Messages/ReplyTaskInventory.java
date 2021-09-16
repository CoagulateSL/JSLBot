package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ReplyTaskInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 290; }
	@Nonnull
    public final String getName() { return "ReplyTaskInventory"; }
	@Nonnull
    @Sequence(0)
	public ReplyTaskInventory_bInventoryData binventorydata=new ReplyTaskInventory_bInventoryData();
}
