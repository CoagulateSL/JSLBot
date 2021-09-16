package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class RequestImage extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 8; }
	@Nonnull
    public final String getName() { return "RequestImage"; }
	@Nonnull
    @Sequence(0)
	public RequestImage_bAgentData bagentdata=new RequestImage_bAgentData();
	@Sequence(1)
	public List<RequestImage_bRequestImage> brequestimage;
	public RequestImage(){}
	public RequestImage(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
