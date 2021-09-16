package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class DirLandReply_bQueryReplies extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vparcelid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(2)
	public BOOL vauction=new BOOL();
	@Nonnull
    @Sequence(3)
	public BOOL vforsale=new BOOL();
	@Nonnull
    @Sequence(4)
	public S32 vsaleprice=new S32();
	@Nonnull
    @Sequence(5)
	public S32 vactualarea=new S32();
}
