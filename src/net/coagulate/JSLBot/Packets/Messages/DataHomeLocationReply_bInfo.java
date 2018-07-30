package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DataHomeLocationReply_bInfo extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public U64 vregionhandle=new U64();
	@Sequence(2)
	public LLVector3 vposition=new LLVector3();
	@Sequence(3)
	public LLVector3 vlookat=new LLVector3();
}
