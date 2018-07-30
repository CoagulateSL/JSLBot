package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestGodlikePowers_bRequestBlock extends Block {
	@Sequence(0)
	public BOOL vgodlike=new BOOL();
	@Sequence(1)
	public LLUUID vtoken=new LLUUID();
}
