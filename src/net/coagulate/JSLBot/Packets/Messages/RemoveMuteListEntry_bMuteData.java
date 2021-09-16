package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class RemoveMuteListEntry_bMuteData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vmuteid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public Variable1 vmutename=new Variable1();
}
