package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UserReport_bReportData extends Block {
	@Sequence(0)
	public U8 vreporttype=new U8();
	@Sequence(1)
	public U8 vcategory=new U8();
	@Sequence(2)
	public LLVector3 vposition=new LLVector3();
	@Sequence(3)
	public U8 vcheckflags=new U8();
	@Sequence(4)
	public LLUUID vscreenshotid=new LLUUID();
	@Sequence(5)
	public LLUUID vobjectid=new LLUUID();
	@Sequence(6)
	public LLUUID vabuserid=new LLUUID();
	@Sequence(7)
	public Variable1 vabuseregionname=new Variable1();
	@Sequence(8)
	public LLUUID vabuseregionid=new LLUUID();
	@Sequence(9)
	public Variable1 vsummary=new Variable1();
	@Sequence(10)
	public Variable2 vdetails=new Variable2();
	@Sequence(11)
	public Variable1 vversionstring=new Variable1();
}
