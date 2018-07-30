package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptQuestion_bData extends Block {
	@Sequence(0)
	public LLUUID vtaskid=new LLUUID();
	@Sequence(1)
	public LLUUID vitemid=new LLUUID();
	@Sequence(2)
	public Variable1 vobjectname=new Variable1();
	@Sequence(3)
	public Variable1 vobjectowner=new Variable1();
	@Sequence(4)
	public S32 vquestions=new S32();
}
