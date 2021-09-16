package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S16;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class ReplyTaskInventory_bInventoryData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vtaskid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public S16 vserial=new S16();
	@Nonnull
    @Sequence(2)
	public Variable1 vfilename=new Variable1();
}
