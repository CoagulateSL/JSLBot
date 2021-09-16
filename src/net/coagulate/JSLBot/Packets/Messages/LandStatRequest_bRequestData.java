package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class LandStatRequest_bRequestData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vreporttype=new U32();
	@Nonnull
    @Sequence(1)
	public U32 vrequestflags=new U32();
	@Nonnull
    @Sequence(2)
	public Variable1 vfilter=new Variable1();
	@Nonnull
    @Sequence(3)
	public S32 vparcellocalid=new S32();
}
