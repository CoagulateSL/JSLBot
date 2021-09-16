package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class RegionInfo_bRegionInfo extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vsimname=new Variable1();
	@Nonnull
    @Sequence(1)
	public U32 vestateid=new U32();
	@Nonnull
    @Sequence(2)
	public U32 vparentestateid=new U32();
	@Nonnull
    @Sequence(3)
	public U32 vregionflags=new U32();
	@Nonnull
    @Sequence(4)
	public U8 vsimaccess=new U8();
	@Nonnull
    @Sequence(5)
	public U8 vmaxagents=new U8();
	@Nonnull
    @Sequence(6)
	public F32 vbillablefactor=new F32();
	@Nonnull
    @Sequence(7)
	public F32 vobjectbonusfactor=new F32();
	@Nonnull
    @Sequence(8)
	public F32 vwaterheight=new F32();
	@Nonnull
    @Sequence(9)
	public F32 vterrainraiselimit=new F32();
	@Nonnull
    @Sequence(10)
	public F32 vterrainlowerlimit=new F32();
	@Nonnull
    @Sequence(11)
	public S32 vpricepermeter=new S32();
	@Nonnull
    @Sequence(12)
	public S32 vredirectgridx=new S32();
	@Nonnull
    @Sequence(13)
	public S32 vredirectgridy=new S32();
	@Nonnull
    @Sequence(14)
	public BOOL vuseestatesun=new BOOL();
	@Nonnull
    @Sequence(15)
	public F32 vsunhour=new F32();
}
