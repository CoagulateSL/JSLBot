package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RevokePermissions extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 193; }
	@Nonnull
    public final String getName() { return "RevokePermissions"; }
	@Nonnull
    @Sequence(0)
	public RevokePermissions_bAgentData bagentdata=new RevokePermissions_bAgentData();
	@Nonnull
    @Sequence(1)
	public RevokePermissions_bData bdata=new RevokePermissions_bData();
	public RevokePermissions(){}
	public RevokePermissions(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
