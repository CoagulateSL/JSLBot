package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class DeRezObject_bAgentBlock extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vgroupid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U8 vdestination=new U8();
	@Nonnull
    @Sequence(2)
	public LLUUID vdestinationid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public LLUUID vtransactionid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public U8 vpacketcount=new U8();
	@Nonnull
    @Sequence(5)
	public U8 vpacketnumber=new U8();
}
