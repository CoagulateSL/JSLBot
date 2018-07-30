package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptSensorRequest_bRequester extends Block {
	@Sequence(0)
	public LLUUID vsourceid=new LLUUID();
	@Sequence(1)
	public LLUUID vrequestid=new LLUUID();
	@Sequence(2)
	public LLUUID vsearchid=new LLUUID();
	@Sequence(3)
	public LLVector3 vsearchpos=new LLVector3();
	@Sequence(4)
	public LLQuaternion vsearchdir=new LLQuaternion();
	@Sequence(5)
	public Variable1 vsearchname=new Variable1();
	@Sequence(6)
	public S32 vtype=new S32();
	@Sequence(7)
	public F32 vrange=new F32();
	@Sequence(8)
	public F32 varc=new F32();
	@Sequence(9)
	public U64 vregionhandle=new U64();
	@Sequence(10)
	public U8 vsearchregions=new U8();
}
