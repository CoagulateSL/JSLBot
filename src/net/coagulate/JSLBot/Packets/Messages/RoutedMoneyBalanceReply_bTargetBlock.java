package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.IPADDR;
import net.coagulate.JSLBot.Packets.Types.IPPORT;

import javax.annotation.Nonnull;

public class RoutedMoneyBalanceReply_bTargetBlock extends Block {
	@Nonnull
    @Sequence(0)
	public IPADDR vtargetip=new IPADDR();
	@Nonnull
    @Sequence(1)
	public IPPORT vtargetport=new IPPORT();
}
