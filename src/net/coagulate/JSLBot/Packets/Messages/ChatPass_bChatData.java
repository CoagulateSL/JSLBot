package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ChatPass_bChatData extends Block {
	@Sequence(0)
	public S32 vchannel=new S32();
	@Sequence(1)
	public LLVector3 vposition=new LLVector3();
	@Sequence(2)
	public LLUUID vid=new LLUUID();
	@Sequence(3)
	public LLUUID vownerid=new LLUUID();
	@Sequence(4)
	public Variable1 vname=new Variable1();
	@Sequence(5)
	public U8 vsourcetype=new U8();
	@Sequence(6)
	public U8 vtype=new U8();
	@Sequence(7)
	public F32 vradius=new F32();
	@Sequence(8)
	public U8 vsimaccess=new U8();
	@Sequence(9)
	public Variable2 vmessage=new Variable2();
}
