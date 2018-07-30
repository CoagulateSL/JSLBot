package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GrantUserRights_bRights extends Block {
	@Sequence(0)
	public LLUUID vagentrelated=new LLUUID();
	@Sequence(1)
	public S32 vrelatedrights=new S32();
}
