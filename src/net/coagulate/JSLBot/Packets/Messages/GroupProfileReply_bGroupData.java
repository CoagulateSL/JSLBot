package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class GroupProfileReply_bGroupData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vgroupid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(2)
	public Variable2 vcharter=new Variable2();
	@Nonnull
    @Sequence(3)
	public BOOL vshowinlist=new BOOL();
	@Nonnull
    @Sequence(4)
	public Variable1 vmembertitle=new Variable1();
	@Nonnull
    @Sequence(5)
	public U64 vpowersmask=new U64();
	@Nonnull
    @Sequence(6)
	public LLUUID vinsigniaid=new LLUUID();
	@Nonnull
    @Sequence(7)
	public LLUUID vfounderid=new LLUUID();
	@Nonnull
    @Sequence(8)
	public S32 vmembershipfee=new S32();
	@Nonnull
    @Sequence(9)
	public BOOL vopenenrollment=new BOOL();
	@Nonnull
    @Sequence(10)
	public S32 vmoney=new S32();
	@Nonnull
    @Sequence(11)
	public S32 vgroupmembershipcount=new S32();
	@Nonnull
    @Sequence(12)
	public S32 vgrouprolescount=new S32();
	@Nonnull
    @Sequence(13)
	public BOOL vallowpublish=new BOOL();
	@Nonnull
    @Sequence(14)
	public BOOL vmaturepublish=new BOOL();
	@Nonnull
    @Sequence(15)
	public LLUUID vownerrole=new LLUUID();
}
