package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarPropertiesReply_bPropertiesData extends Block {
	@Sequence(0)
	public LLUUID vimageid=new LLUUID();
	@Sequence(1)
	public LLUUID vflimageid=new LLUUID();
	@Sequence(2)
	public LLUUID vpartnerid=new LLUUID();
	@Sequence(3)
	public Variable2 vabouttext=new Variable2();
	@Sequence(4)
	public Variable1 vflabouttext=new Variable1();
	@Sequence(5)
	public Variable1 vbornon=new Variable1();
	@Sequence(6)
	public Variable1 vprofileurl=new Variable1();
	@Sequence(7)
	public Variable1 vchartermember=new Variable1();
	@Sequence(8)
	public U32 vflags=new U32();
}
