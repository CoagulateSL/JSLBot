package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectExtraParams_bObjectData extends Block {
	@Sequence(0)
	public U32 vobjectlocalid=new U32();
	@Sequence(1)
	public U16 vparamtype=new U16();
	@Sequence(2)
	public BOOL vparaminuse=new BOOL();
	@Sequence(3)
	public U32 vparamsize=new U32();
	@Sequence(4)
	public Variable1 vparamdata=new Variable1();
}
