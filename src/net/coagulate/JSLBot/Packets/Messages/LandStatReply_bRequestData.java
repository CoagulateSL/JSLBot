package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LandStatReply_bRequestData extends Block {
	@Sequence(0)
	public U32 vreporttype=new U32();
	@Sequence(1)
	public U32 vrequestflags=new U32();
	@Sequence(2)
	public U32 vtotalobjectcount=new U32();
}
