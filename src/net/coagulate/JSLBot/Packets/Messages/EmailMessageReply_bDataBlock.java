package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class EmailMessageReply_bDataBlock extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U32 vmore=new U32();
	@Nonnull
    @Sequence(2)
	public U32 vtime=new U32();
	@Nonnull
    @Sequence(3)
	public Variable1 vfromaddress=new Variable1();
	@Nonnull
    @Sequence(4)
	public Variable1 vsubject=new Variable1();
	@Nonnull
    @Sequence(5)
	public Variable2 vdata=new Variable2();
	@Nonnull
    @Sequence(6)
	public Variable1 vmailfilter=new Variable1();
}
