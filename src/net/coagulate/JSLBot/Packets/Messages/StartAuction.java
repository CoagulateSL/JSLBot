package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class StartAuction extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 229; }
	@Nonnull
    public final String getName() { return "StartAuction"; }
	@Nonnull
    @Sequence(0)
	public StartAuction_bAgentData bagentdata=new StartAuction_bAgentData();
	@Nonnull
    @Sequence(1)
	public StartAuction_bParcelData bparceldata=new StartAuction_bParcelData();
	public StartAuction(){}
	public StartAuction(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
