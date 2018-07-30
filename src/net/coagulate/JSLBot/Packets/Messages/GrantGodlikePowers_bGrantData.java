package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GrantGodlikePowers_bGrantData extends Block {
	@Sequence(0)
	public U8 vgodlevel=new U8();
	@Sequence(1)
	public LLUUID vtoken=new LLUUID();
}
