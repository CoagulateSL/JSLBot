package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupAccountSummaryReply_bMoneyData extends Block {
	@Sequence(0)
	public LLUUID vrequestid=new LLUUID();
	@Sequence(1)
	public S32 vintervaldays=new S32();
	@Sequence(2)
	public S32 vcurrentinterval=new S32();
	@Sequence(3)
	public Variable1 vstartdate=new Variable1();
	@Sequence(4)
	public S32 vbalance=new S32();
	@Sequence(5)
	public S32 vtotalcredits=new S32();
	@Sequence(6)
	public S32 vtotaldebits=new S32();
	@Sequence(7)
	public S32 vobjecttaxcurrent=new S32();
	@Sequence(8)
	public S32 vlighttaxcurrent=new S32();
	@Sequence(9)
	public S32 vlandtaxcurrent=new S32();
	@Sequence(10)
	public S32 vgrouptaxcurrent=new S32();
	@Sequence(11)
	public S32 vparceldirfeecurrent=new S32();
	@Sequence(12)
	public S32 vobjecttaxestimate=new S32();
	@Sequence(13)
	public S32 vlighttaxestimate=new S32();
	@Sequence(14)
	public S32 vlandtaxestimate=new S32();
	@Sequence(15)
	public S32 vgrouptaxestimate=new S32();
	@Sequence(16)
	public S32 vparceldirfeeestimate=new S32();
	@Sequence(17)
	public S32 vnonexemptmembers=new S32();
	@Sequence(18)
	public Variable1 vlasttaxdate=new Variable1();
	@Sequence(19)
	public Variable1 vtaxdate=new Variable1();
}
