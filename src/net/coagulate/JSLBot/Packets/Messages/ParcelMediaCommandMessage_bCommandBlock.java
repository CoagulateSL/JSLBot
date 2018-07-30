package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelMediaCommandMessage_bCommandBlock extends Block {
	@Sequence(0)
	public U32 vflags=new U32();
	@Sequence(1)
	public U32 vcommand=new U32();
	@Sequence(2)
	public F32 vtime=new F32();
}
