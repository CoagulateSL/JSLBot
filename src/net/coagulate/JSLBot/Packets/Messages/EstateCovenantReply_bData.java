package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class EstateCovenantReply_bData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vcovenantid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U32 vcovenanttimestamp=new U32();
	@Nonnull
    @Sequence(2)
	public Variable1 vestatename=new Variable1();
	@Nonnull
    @Sequence(3)
	public LLUUID vestateownerid=new LLUUID();
}
