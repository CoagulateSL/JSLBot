package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ChatFromSimulator_bChatData extends Block {
	@Sequence(0)
	public Variable1 vfromname=new Variable1();
	@Sequence(1)
	public LLUUID vsourceid=new LLUUID();
	@Sequence(2)
	public LLUUID vownerid=new LLUUID();
	@Sequence(3)
	public U8 vsourcetype=new U8();
	@Sequence(4)
	public U8 vchattype=new U8();
	@Sequence(5)
	public U8 vaudible=new U8();
	@Sequence(6)
	public LLVector3 vposition=new LLVector3();
	@Sequence(7)
	public Variable2 vmessage=new Variable2();
}
