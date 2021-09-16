package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class GroupNoticesListReply_bData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vnoticeid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U32 vtimestamp=new U32();
	@Nonnull
    @Sequence(2)
	public Variable2 vfromname=new Variable2();
	@Nonnull
    @Sequence(3)
	public Variable2 vsubject=new Variable2();
	@Nonnull
    @Sequence(4)
	public BOOL vhasattachment=new BOOL();
	@Nonnull
    @Sequence(5)
	public U8 vassettype=new U8();
}
