package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class LogoutReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 253; }
	@Nonnull
    public final String getName() { return "LogoutReply"; }
	@Nonnull
    @Sequence(0)
	public LogoutReply_bAgentData bagentdata=new LogoutReply_bAgentData();
	@Sequence(1)
	public List<LogoutReply_bInventoryData> binventorydata;
	public LogoutReply(){}
	public LogoutReply(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
