package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class TransferInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 295; }
	@Nonnull
    public final String getName() { return "TransferInventory"; }
	@Nonnull
    @Sequence(0)
	public TransferInventory_bInfoBlock binfoblock=new TransferInventory_bInfoBlock();
	@Sequence(1)
	public List<TransferInventory_bInventoryBlock> binventoryblock;
	@Nonnull
    @Sequence(2)
	public TransferInventory_bValidationBlock bvalidationblock=new TransferInventory_bValidationBlock();
}
