package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class StartPingCheck_bPingID extends Block {
	@Sequence(0)
	public U8 vpingid=new U8();
	@Sequence(1)
	public U32 voldestunacked=new U32();
}
