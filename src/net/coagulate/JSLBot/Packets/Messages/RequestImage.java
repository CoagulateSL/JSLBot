package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestImage extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 8; }
	public final String getName() { return "RequestImage"; }
	@Sequence(0)
	public RequestImage_bAgentData bagentdata=new RequestImage_bAgentData();
	@Sequence(1)
	public List<RequestImage_bRequestImage> brequestimage;
	public RequestImage(){}
	public RequestImage(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
