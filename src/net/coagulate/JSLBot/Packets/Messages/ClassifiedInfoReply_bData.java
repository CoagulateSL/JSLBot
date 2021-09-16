package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class ClassifiedInfoReply_bData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vclassifiedid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vcreatorid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public U32 vcreationdate=new U32();
	@Nonnull
    @Sequence(3)
	public U32 vexpirationdate=new U32();
	@Nonnull
    @Sequence(4)
	public U32 vcategory=new U32();
	@Nonnull
    @Sequence(5)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(6)
	public Variable2 vdesc=new Variable2();
	@Nonnull
    @Sequence(7)
	public LLUUID vparcelid=new LLUUID();
	@Nonnull
    @Sequence(8)
	public U32 vparentestate=new U32();
	@Nonnull
    @Sequence(9)
	public LLUUID vsnapshotid=new LLUUID();
	@Nonnull
    @Sequence(10)
	public Variable1 vsimname=new Variable1();
	@Nonnull
    @Sequence(11)
	public LLVector3d vposglobal=new LLVector3d();
	@Nonnull
    @Sequence(12)
	public Variable1 vparcelname=new Variable1();
	@Nonnull
    @Sequence(13)
	public U8 vclassifiedflags=new U8();
	@Nonnull
    @Sequence(14)
	public S32 vpriceforlisting=new S32();
}
