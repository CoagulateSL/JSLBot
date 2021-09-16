package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U64;

import javax.annotation.Nonnull;

public class SetStartLocation_bStartLocationData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vregionid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public U32 vlocationid=new U32();
	@Nonnull
    @Sequence(3)
	public U64 vregionhandle=new U64();
	@Nonnull
    @Sequence(4)
	public LLVector3 vlocationpos=new LLVector3();
	@Nonnull
    @Sequence(5)
	public LLVector3 vlocationlookat=new LLVector3();
}
