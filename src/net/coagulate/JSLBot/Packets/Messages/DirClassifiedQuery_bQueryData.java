package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirClassifiedQuery_bQueryData extends Block {
	@Sequence(0)
	public LLUUID vqueryid=new LLUUID();
	@Sequence(1)
	public Variable1 vquerytext=new Variable1();
	@Sequence(2)
	public U32 vqueryflags=new U32();
	@Sequence(3)
	public U32 vcategory=new U32();
	@Sequence(4)
	public S32 vquerystart=new S32();
}
