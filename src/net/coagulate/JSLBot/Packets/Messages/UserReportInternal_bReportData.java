package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class UserReportInternal_bReportData extends Block {
	@Nonnull
    @Sequence(0)
	public U8 vreporttype=new U8();
	@Nonnull
    @Sequence(1)
	public U8 vcategory=new U8();
	@Nonnull
    @Sequence(2)
	public LLUUID vreporterid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public LLVector3 vviewerposition=new LLVector3();
	@Nonnull
    @Sequence(4)
	public LLVector3 vagentposition=new LLVector3();
	@Nonnull
    @Sequence(5)
	public LLUUID vscreenshotid=new LLUUID();
	@Nonnull
    @Sequence(6)
	public LLUUID vobjectid=new LLUUID();
	@Nonnull
    @Sequence(7)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(8)
	public LLUUID vlastownerid=new LLUUID();
	@Nonnull
    @Sequence(9)
	public LLUUID vcreatorid=new LLUUID();
	@Nonnull
    @Sequence(10)
	public LLUUID vregionid=new LLUUID();
	@Nonnull
    @Sequence(11)
	public LLUUID vabuserid=new LLUUID();
	@Nonnull
    @Sequence(12)
	public Variable1 vabuseregionname=new Variable1();
	@Nonnull
    @Sequence(13)
	public LLUUID vabuseregionid=new LLUUID();
	@Nonnull
    @Sequence(14)
	public Variable1 vsummary=new Variable1();
	@Nonnull
    @Sequence(15)
	public Variable2 vdetails=new Variable2();
	@Nonnull
    @Sequence(16)
	public Variable1 vversionstring=new Variable1();
}
