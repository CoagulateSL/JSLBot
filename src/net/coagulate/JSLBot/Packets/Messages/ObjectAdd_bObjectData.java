package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class ObjectAdd_bObjectData extends Block {
	@Nonnull
    @Sequence(0)
	public U8 vpcode=new U8();
	@Nonnull
    @Sequence(1)
	public U8 vmaterial=new U8();
	@Nonnull
    @Sequence(2)
	public U32 vaddflags=new U32();
	@Nonnull
    @Sequence(3)
	public U8 vpathcurve=new U8();
	@Nonnull
    @Sequence(4)
	public U8 vprofilecurve=new U8();
	@Nonnull
    @Sequence(5)
	public U16 vpathbegin=new U16();
	@Nonnull
    @Sequence(6)
	public U16 vpathend=new U16();
	@Nonnull
    @Sequence(7)
	public U8 vpathscalex=new U8();
	@Nonnull
    @Sequence(8)
	public U8 vpathscaley=new U8();
	@Nonnull
    @Sequence(9)
	public U8 vpathshearx=new U8();
	@Nonnull
    @Sequence(10)
	public U8 vpathsheary=new U8();
	@Nonnull
    @Sequence(11)
	public S8 vpathtwist=new S8();
	@Nonnull
    @Sequence(12)
	public S8 vpathtwistbegin=new S8();
	@Nonnull
    @Sequence(13)
	public S8 vpathradiusoffset=new S8();
	@Nonnull
    @Sequence(14)
	public S8 vpathtaperx=new S8();
	@Nonnull
    @Sequence(15)
	public S8 vpathtapery=new S8();
	@Nonnull
    @Sequence(16)
	public U8 vpathrevolutions=new U8();
	@Nonnull
    @Sequence(17)
	public S8 vpathskew=new S8();
	@Nonnull
    @Sequence(18)
	public U16 vprofilebegin=new U16();
	@Nonnull
    @Sequence(19)
	public U16 vprofileend=new U16();
	@Nonnull
    @Sequence(20)
	public U16 vprofilehollow=new U16();
	@Nonnull
    @Sequence(21)
	public U8 vbypassraycast=new U8();
	@Nonnull
    @Sequence(22)
	public LLVector3 vraystart=new LLVector3();
	@Nonnull
    @Sequence(23)
	public LLVector3 vrayend=new LLVector3();
	@Nonnull
    @Sequence(24)
	public LLUUID vraytargetid=new LLUUID();
	@Nonnull
    @Sequence(25)
	public U8 vrayendisintersection=new U8();
	@Nonnull
    @Sequence(26)
	public LLVector3 vscale=new LLVector3();
	@Nonnull
    @Sequence(27)
	public LLQuaternion vrotation=new LLQuaternion();
	@Nonnull
    @Sequence(28)
	public U8 vstate=new U8();
}
