package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class EmailMessageRequest_bDataBlock extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public Variable1 vfromaddress=new Variable1();
	@Nonnull
    @Sequence(2)
	public Variable1 vsubject=new Variable1();
}
