package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptMailRegistration_bDataBlock extends Block {
	@Sequence(0)
	public Variable1 vtargetip=new Variable1();
	@Sequence(1)
	public IPPORT vtargetport=new IPPORT();
	@Sequence(2)
	public LLUUID vtaskid=new LLUUID();
	@Sequence(3)
	public U32 vflags=new U32();
}
