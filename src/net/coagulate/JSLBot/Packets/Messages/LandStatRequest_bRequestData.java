package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LandStatRequest_bRequestData extends Block {
	@Sequence(0)
	public U32 vreporttype=new U32();
	@Sequence(1)
	public U32 vrequestflags=new U32();
	@Sequence(2)
	public Variable1 vfilter=new Variable1();
	@Sequence(3)
	public S32 vparcellocalid=new S32();
}
