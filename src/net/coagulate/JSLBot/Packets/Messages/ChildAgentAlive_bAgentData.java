package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ChildAgentAlive_bAgentData extends Block {
	@Sequence(0)
	public U64 vregionhandle=new U64();
	@Sequence(1)
	public U32 vviewercircuitcode=new U32();
	@Sequence(2)
	public LLUUID vagentid=new LLUUID();
	@Sequence(3)
	public LLUUID vsessionid=new LLUUID();
}
