package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UUIDNameReply_bUUIDNameBlock extends Block {
	@Sequence(0)
	public LLUUID vid=new LLUUID();
	@Sequence(1)
	public Variable1 vfirstname=new Variable1();
	@Sequence(2)
	public Variable1 vlastname=new Variable1();
}
