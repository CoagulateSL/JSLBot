package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UserReportInternal_bReportData extends Block {
	@Sequence(0)
	public U8 vreporttype=new U8();
	@Sequence(1)
	public U8 vcategory=new U8();
	@Sequence(2)
	public LLUUID vreporterid=new LLUUID();
	@Sequence(3)
	public LLVector3 vviewerposition=new LLVector3();
	@Sequence(4)
	public LLVector3 vagentposition=new LLVector3();
	@Sequence(5)
	public LLUUID vscreenshotid=new LLUUID();
	@Sequence(6)
	public LLUUID vobjectid=new LLUUID();
	@Sequence(7)
	public LLUUID vownerid=new LLUUID();
	@Sequence(8)
	public LLUUID vlastownerid=new LLUUID();
	@Sequence(9)
	public LLUUID vcreatorid=new LLUUID();
	@Sequence(10)
	public LLUUID vregionid=new LLUUID();
	@Sequence(11)
	public LLUUID vabuserid=new LLUUID();
	@Sequence(12)
	public Variable1 vabuseregionname=new Variable1();
	@Sequence(13)
	public LLUUID vabuseregionid=new LLUUID();
	@Sequence(14)
	public Variable1 vsummary=new Variable1();
	@Sequence(15)
	public Variable2 vdetails=new Variable2();
	@Sequence(16)
	public Variable1 vversionstring=new Variable1();
}
