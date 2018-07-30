package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptTeleportRequest_bData extends Block {
	@Sequence(0)
	public Variable1 vobjectname=new Variable1();
	@Sequence(1)
	public Variable1 vsimname=new Variable1();
	@Sequence(2)
	public LLVector3 vsimposition=new LLVector3();
	@Sequence(3)
	public LLVector3 vlookat=new LLVector3();
}
