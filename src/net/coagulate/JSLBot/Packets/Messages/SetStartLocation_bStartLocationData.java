package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SetStartLocation_bStartLocationData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public LLUUID vregionid=new LLUUID();
	@Sequence(2)
	public U32 vlocationid=new U32();
	@Sequence(3)
	public U64 vregionhandle=new U64();
	@Sequence(4)
	public LLVector3 vlocationpos=new LLVector3();
	@Sequence(5)
	public LLVector3 vlocationlookat=new LLVector3();
}
