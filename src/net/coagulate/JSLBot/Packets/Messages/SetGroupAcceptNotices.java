package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class SetGroupAcceptNotices extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 370; }
	@Nonnull
    public final String getName() { return "SetGroupAcceptNotices"; }
	@Nonnull
    @Sequence(0)
	public SetGroupAcceptNotices_bAgentData bagentdata=new SetGroupAcceptNotices_bAgentData();
	@Nonnull
    @Sequence(1)
	public SetGroupAcceptNotices_bData bdata=new SetGroupAcceptNotices_bData();
	@Nonnull
    @Sequence(2)
	public SetGroupAcceptNotices_bNewData bnewdata=new SetGroupAcceptNotices_bNewData();
	public SetGroupAcceptNotices(){}
	public SetGroupAcceptNotices(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
