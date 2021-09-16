package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.S8;
import net.coagulate.JSLBot.Packets.Types.U16;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class ObjectShape_bObjectData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vobjectlocalid=new U32();
	@Nonnull
    @Sequence(1)
	public U8 vpathcurve=new U8();
	@Nonnull
    @Sequence(2)
	public U8 vprofilecurve=new U8();
	@Nonnull
    @Sequence(3)
	public U16 vpathbegin=new U16();
	@Nonnull
    @Sequence(4)
	public U16 vpathend=new U16();
	@Nonnull
    @Sequence(5)
	public U8 vpathscalex=new U8();
	@Nonnull
    @Sequence(6)
	public U8 vpathscaley=new U8();
	@Nonnull
    @Sequence(7)
	public U8 vpathshearx=new U8();
	@Nonnull
    @Sequence(8)
	public U8 vpathsheary=new U8();
	@Nonnull
    @Sequence(9)
	public S8 vpathtwist=new S8();
	@Nonnull
    @Sequence(10)
	public S8 vpathtwistbegin=new S8();
	@Nonnull
    @Sequence(11)
	public S8 vpathradiusoffset=new S8();
	@Nonnull
    @Sequence(12)
	public S8 vpathtaperx=new S8();
	@Nonnull
    @Sequence(13)
	public S8 vpathtapery=new S8();
	@Nonnull
    @Sequence(14)
	public U8 vpathrevolutions=new U8();
	@Nonnull
    @Sequence(15)
	public S8 vpathskew=new S8();
	@Nonnull
    @Sequence(16)
	public U16 vprofilebegin=new U16();
	@Nonnull
    @Sequence(17)
	public U16 vprofileend=new U16();
	@Nonnull
    @Sequence(18)
	public U16 vprofilehollow=new U16();
}
