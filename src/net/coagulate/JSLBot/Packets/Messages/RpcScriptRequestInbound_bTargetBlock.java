package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RpcScriptRequestInbound_bTargetBlock extends Block {
	@Sequence(0)
	public U32 vgridx=new U32();
	@Sequence(1)
	public U32 vgridy=new U32();
}
