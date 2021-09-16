package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class AvatarInterestsReply_bPropertiesData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vwanttomask=new U32();
	@Nonnull
    @Sequence(1)
	public Variable1 vwanttotext=new Variable1();
	@Nonnull
    @Sequence(2)
	public U32 vskillsmask=new U32();
	@Nonnull
    @Sequence(3)
	public Variable1 vskillstext=new Variable1();
	@Nonnull
    @Sequence(4)
	public Variable1 vlanguagestext=new Variable1();
}
