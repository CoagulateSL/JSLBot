package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SendXferPacket_bDataPacket extends Block {
	@Sequence(0)
	public Variable2 vdata=new Variable2();
}
