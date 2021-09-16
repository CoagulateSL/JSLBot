package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class RequestParcelTransfer_bData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vtransactionid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U32 vtransactiontime=new U32();
	@Nonnull
    @Sequence(2)
	public LLUUID vsourceid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public LLUUID vdestid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(5)
	public U8 vflags=new U8();
	@Nonnull
    @Sequence(6)
	public S32 vtransactiontype=new S32();
	@Nonnull
    @Sequence(7)
	public S32 vamount=new S32();
	@Nonnull
    @Sequence(8)
	public S32 vbillablearea=new S32();
	@Nonnull
    @Sequence(9)
	public S32 vactualarea=new S32();
	@Nonnull
    @Sequence(10)
	public BOOL vfinal=new BOOL();
}
