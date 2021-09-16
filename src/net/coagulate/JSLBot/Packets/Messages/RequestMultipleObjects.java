package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class RequestMultipleObjects extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 3; }
	@Nonnull
    public final String getName() { return "RequestMultipleObjects"; }
	@Nonnull
    @Sequence(0)
	public RequestMultipleObjects_bAgentData bagentdata=new RequestMultipleObjects_bAgentData();
	@Sequence(1)
	public List<RequestMultipleObjects_bObjectData> bobjectdata;
	public RequestMultipleObjects(){}
	public RequestMultipleObjects(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
