package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SetStartLocationRequest_bStartLocationData extends Block {
	@Sequence(0)
	public Variable1 vsimname=new Variable1();
	@Sequence(1)
	public U32 vlocationid=new U32();
	@Sequence(2)
	public LLVector3 vlocationpos=new LLVector3();
	@Sequence(3)
	public LLVector3 vlocationlookat=new LLVector3();
}
