package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LandStatReply_bReportData extends Block {
	@Sequence(0)
	public U32 vtasklocalid=new U32();
	@Sequence(1)
	public LLUUID vtaskid=new LLUUID();
	@Sequence(2)
	public F32 vlocationx=new F32();
	@Sequence(3)
	public F32 vlocationy=new F32();
	@Sequence(4)
	public F32 vlocationz=new F32();
	@Sequence(5)
	public F32 vscore=new F32();
	@Sequence(6)
	public Variable1 vtaskname=new Variable1();
	@Sequence(7)
	public Variable1 vownername=new Variable1();
}
