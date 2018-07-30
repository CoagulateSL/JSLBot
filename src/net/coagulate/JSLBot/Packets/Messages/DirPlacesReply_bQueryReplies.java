package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirPlacesReply_bQueryReplies extends Block {
	@Sequence(0)
	public LLUUID vparcelid=new LLUUID();
	@Sequence(1)
	public Variable1 vname=new Variable1();
	@Sequence(2)
	public BOOL vforsale=new BOOL();
	@Sequence(3)
	public BOOL vauction=new BOOL();
	@Sequence(4)
	public F32 vdwell=new F32();
}
