package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class EjectUser extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 167; }
	@Nonnull
    public final String getName() { return "EjectUser"; }
	@Nonnull
    @Sequence(0)
	public EjectUser_bAgentData bagentdata=new EjectUser_bAgentData();
	@Nonnull
    @Sequence(1)
	public EjectUser_bData bdata=new EjectUser_bData();
	public EjectUser(){}
	public EjectUser(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
