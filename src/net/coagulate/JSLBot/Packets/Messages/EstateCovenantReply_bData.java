package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EstateCovenantReply_bData extends Block {
	@Sequence(0)
	public LLUUID vcovenantid=new LLUUID();
	@Sequence(1)
	public U32 vcovenanttimestamp=new U32();
	@Sequence(2)
	public Variable1 vestatename=new Variable1();
	@Sequence(3)
	public LLUUID vestateownerid=new LLUUID();
}
