package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.IPADDR;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

import javax.annotation.Nonnull;

public class FindAgent_bAgentBlock extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vhunter=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vprey=new LLUUID();
	@Nonnull
    @Sequence(2)
	public IPADDR vspaceip=new IPADDR();
}
