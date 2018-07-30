package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TelehubInfo_bTelehubBlock extends Block {
	@Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Sequence(1)
	public Variable1 vobjectname=new Variable1();
	@Sequence(2)
	public LLVector3 vtelehubpos=new LLVector3();
	@Sequence(3)
	public LLQuaternion vtelehubrot=new LLQuaternion();
}
