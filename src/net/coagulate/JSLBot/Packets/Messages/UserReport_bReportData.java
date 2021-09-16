package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class UserReport_bReportData extends Block {
	@Nonnull
    @Sequence(0)
	public U8 vreporttype=new U8();
	@Nonnull
    @Sequence(1)
	public U8 vcategory=new U8();
	@Nonnull
    @Sequence(2)
	public LLVector3 vposition=new LLVector3();
	@Nonnull
    @Sequence(3)
	public U8 vcheckflags=new U8();
	@Nonnull
    @Sequence(4)
	public LLUUID vscreenshotid=new LLUUID();
	@Nonnull
    @Sequence(5)
	public LLUUID vobjectid=new LLUUID();
	@Nonnull
    @Sequence(6)
	public LLUUID vabuserid=new LLUUID();
	@Nonnull
    @Sequence(7)
	public Variable1 vabuseregionname=new Variable1();
	@Nonnull
    @Sequence(8)
	public LLUUID vabuseregionid=new LLUUID();
	@Nonnull
    @Sequence(9)
	public Variable1 vsummary=new Variable1();
	@Nonnull
    @Sequence(10)
	public Variable2 vdetails=new Variable2();
	@Nonnull
    @Sequence(11)
	public Variable1 vversionstring=new Variable1();
}
