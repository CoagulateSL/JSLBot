package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class PlacesQuery_bQueryData extends Block {
	@Sequence(0)
	public Variable1 vquerytext=new Variable1();
	@Sequence(1)
	public U32 vqueryflags=new U32();
	@Sequence(2)
	public S8 vcategory=new S8();
	@Sequence(3)
	public Variable1 vsimname=new Variable1();
}
