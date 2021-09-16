package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class LandStatReply_bReportData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vtasklocalid=new U32();
	@Nonnull
    @Sequence(1)
	public LLUUID vtaskid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public F32 vlocationx=new F32();
	@Nonnull
    @Sequence(3)
	public F32 vlocationy=new F32();
	@Nonnull
    @Sequence(4)
	public F32 vlocationz=new F32();
	@Nonnull
    @Sequence(5)
	public F32 vscore=new F32();
	@Nonnull
    @Sequence(6)
	public Variable1 vtaskname=new Variable1();
	@Nonnull
    @Sequence(7)
	public Variable1 vownername=new Variable1();
}
