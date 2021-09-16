package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class FreezeUser extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 168; }
	@Nonnull
    public final String getName() { return "FreezeUser"; }
	@Nonnull
    @Sequence(0)
	public FreezeUser_bAgentData bagentdata=new FreezeUser_bAgentData();
	@Nonnull
    @Sequence(1)
	public FreezeUser_bData bdata=new FreezeUser_bData();
	public FreezeUser(){}
	public FreezeUser(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
