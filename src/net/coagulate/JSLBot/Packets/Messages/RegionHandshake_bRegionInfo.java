package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class RegionHandshake_bRegionInfo extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vregionflags=new U32();
	@Nonnull
    @Sequence(1)
	public U8 vsimaccess=new U8();
	@Nonnull
    @Sequence(2)
	public Variable1 vsimname=new Variable1();
	@Nonnull
    @Sequence(3)
	public LLUUID vsimowner=new LLUUID();
	@Nonnull
    @Sequence(4)
	public BOOL visestatemanager=new BOOL();
	@Nonnull
    @Sequence(5)
	public F32 vwaterheight=new F32();
	@Nonnull
    @Sequence(6)
	public F32 vbillablefactor=new F32();
	@Nonnull
    @Sequence(7)
	public LLUUID vcacheid=new LLUUID();
	@Nonnull
    @Sequence(8)
	public LLUUID vterrainbase0=new LLUUID();
	@Nonnull
    @Sequence(9)
	public LLUUID vterrainbase1=new LLUUID();
	@Nonnull
    @Sequence(10)
	public LLUUID vterrainbase2=new LLUUID();
	@Nonnull
    @Sequence(11)
	public LLUUID vterrainbase3=new LLUUID();
	@Nonnull
    @Sequence(12)
	public LLUUID vterraindetail0=new LLUUID();
	@Nonnull
    @Sequence(13)
	public LLUUID vterraindetail1=new LLUUID();
	@Nonnull
    @Sequence(14)
	public LLUUID vterraindetail2=new LLUUID();
	@Nonnull
    @Sequence(15)
	public LLUUID vterraindetail3=new LLUUID();
	@Nonnull
    @Sequence(16)
	public F32 vterrainstartheight00=new F32();
	@Nonnull
    @Sequence(17)
	public F32 vterrainstartheight01=new F32();
	@Nonnull
    @Sequence(18)
	public F32 vterrainstartheight10=new F32();
	@Nonnull
    @Sequence(19)
	public F32 vterrainstartheight11=new F32();
	@Nonnull
    @Sequence(20)
	public F32 vterrainheightrange00=new F32();
	@Nonnull
    @Sequence(21)
	public F32 vterrainheightrange01=new F32();
	@Nonnull
    @Sequence(22)
	public F32 vterrainheightrange10=new F32();
	@Nonnull
    @Sequence(23)
	public F32 vterrainheightrange11=new F32();
}
