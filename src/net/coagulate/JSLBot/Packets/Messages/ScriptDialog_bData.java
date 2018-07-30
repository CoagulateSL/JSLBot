package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptDialog_bData extends Block {
	@Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Sequence(1)
	public Variable1 vfirstname=new Variable1();
	@Sequence(2)
	public Variable1 vlastname=new Variable1();
	@Sequence(3)
	public Variable1 vobjectname=new Variable1();
	@Sequence(4)
	public Variable2 vmessage=new Variable2();
	@Sequence(5)
	public S32 vchatchannel=new S32();
	@Sequence(6)
	public LLUUID vimageid=new LLUUID();
}
