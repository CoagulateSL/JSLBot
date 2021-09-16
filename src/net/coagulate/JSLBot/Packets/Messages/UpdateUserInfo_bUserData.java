package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class UpdateUserInfo_bUserData extends Block {
	@Nonnull
    @Sequence(0)
	public BOOL vimviaemail=new BOOL();
	@Nonnull
    @Sequence(1)
	public Variable1 vdirectoryvisibility=new Variable1();
}
