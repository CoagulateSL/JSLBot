package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class UserReport extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 133; }
	@Nonnull
    public final String getName() { return "UserReport"; }
	@Nonnull
    @Sequence(0)
	public UserReport_bAgentData bagentdata=new UserReport_bAgentData();
	@Nonnull
    @Sequence(1)
	public UserReport_bReportData breportdata=new UserReport_bReportData();
	public UserReport(){}
	public UserReport(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
