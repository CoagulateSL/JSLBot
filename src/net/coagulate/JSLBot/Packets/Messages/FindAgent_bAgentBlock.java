package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class FindAgent_bAgentBlock extends Block {
	@Sequence(0)
	public LLUUID vhunter=new LLUUID();
	@Sequence(1)
	public LLUUID vprey=new LLUUID();
	@Sequence(2)
	public IPADDR vspaceip=new IPADDR();
}
