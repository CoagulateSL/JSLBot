package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RoutedMoneyBalanceReply_bTargetBlock extends Block {
	@Sequence(0)
	public IPADDR vtargetip=new IPADDR();
	@Sequence(1)
	public IPPORT vtargetport=new IPPORT();
}
