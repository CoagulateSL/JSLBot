package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TeleportRequest_bInfo extends Block {
	@Sequence(0)
	public LLUUID vregionid=new LLUUID();
	@Sequence(1)
	public LLVector3 vposition=new LLVector3();
	@Sequence(2)
	public LLVector3 vlookat=new LLVector3();
}
