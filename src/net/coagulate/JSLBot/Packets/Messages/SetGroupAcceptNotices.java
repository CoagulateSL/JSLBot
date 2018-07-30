package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SetGroupAcceptNotices extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 370; }
	public final String getName() { return "SetGroupAcceptNotices"; }
	@Sequence(0)
	public SetGroupAcceptNotices_bAgentData bagentdata=new SetGroupAcceptNotices_bAgentData();
	@Sequence(1)
	public SetGroupAcceptNotices_bData bdata=new SetGroupAcceptNotices_bData();
	@Sequence(2)
	public SetGroupAcceptNotices_bNewData bnewdata=new SetGroupAcceptNotices_bNewData();
}
