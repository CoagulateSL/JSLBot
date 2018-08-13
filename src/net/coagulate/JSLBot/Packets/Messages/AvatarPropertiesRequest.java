package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarPropertiesRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 169; }
	public final String getName() { return "AvatarPropertiesRequest"; }
	@Sequence(0)
	public AvatarPropertiesRequest_bAgentData bagentdata=new AvatarPropertiesRequest_bAgentData();
	public AvatarPropertiesRequest(){}
	public AvatarPropertiesRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
