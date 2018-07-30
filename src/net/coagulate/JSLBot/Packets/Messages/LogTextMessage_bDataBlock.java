package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LogTextMessage_bDataBlock extends Block {
	@Sequence(0)
	public LLUUID vfromagentid=new LLUUID();
	@Sequence(1)
	public LLUUID vtoagentid=new LLUUID();
	@Sequence(2)
	public F64 vglobalx=new F64();
	@Sequence(3)
	public F64 vglobaly=new F64();
	@Sequence(4)
	public U32 vtime=new U32();
	@Sequence(5)
	public Variable2 vmessage=new Variable2();
}
