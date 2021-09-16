package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class StartAuction_bParcelData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vparcelid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vsnapshotid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public Variable1 vname=new Variable1();
}
