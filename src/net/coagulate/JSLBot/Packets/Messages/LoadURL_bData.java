package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LoadURL_bData extends Block {
	@Sequence(0)
	public Variable1 vobjectname=new Variable1();
	@Sequence(1)
	public LLUUID vobjectid=new LLUUID();
	@Sequence(2)
	public LLUUID vownerid=new LLUUID();
	@Sequence(3)
	public BOOL vownerisgroup=new BOOL();
	@Sequence(4)
	public Variable1 vmessage=new Variable1();
	@Sequence(5)
	public Variable1 vurl=new Variable1();
}
