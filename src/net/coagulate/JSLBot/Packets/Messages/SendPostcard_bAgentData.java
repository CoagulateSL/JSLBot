package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SendPostcard_bAgentData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public LLUUID vsessionid=new LLUUID();
	@Sequence(2)
	public LLUUID vassetid=new LLUUID();
	@Sequence(3)
	public LLVector3d vposglobal=new LLVector3d();
	@Sequence(4)
	public Variable1 vto=new Variable1();
	@Sequence(5)
	public Variable1 vfrom=new Variable1();
	@Sequence(6)
	public Variable1 vname=new Variable1();
	@Sequence(7)
	public Variable1 vsubject=new Variable1();
	@Sequence(8)
	public Variable2 vmsg=new Variable2();
	@Sequence(9)
	public BOOL vallowpublish=new BOOL();
	@Sequence(10)
	public BOOL vmaturepublish=new BOOL();
}
