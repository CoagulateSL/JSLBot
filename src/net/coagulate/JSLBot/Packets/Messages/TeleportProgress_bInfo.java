package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TeleportProgress_bInfo extends Block {
	@Sequence(0)
	public U32 vteleportflags=new U32();
	@Sequence(1)
	public Variable1 vmessage=new Variable1();
}
