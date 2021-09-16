package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class UpdateMuteListEntry_bMuteData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vmuteid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public Variable1 vmutename=new Variable1();
	@Nonnull
    @Sequence(2)
	public S32 vmutetype=new S32();
	@Nonnull
    @Sequence(3)
	public U32 vmuteflags=new U32();
}
