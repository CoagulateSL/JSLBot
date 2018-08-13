package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarPropertiesRequestBackend extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 170; }
	public final String getName() { return "AvatarPropertiesRequestBackend"; }
	@Sequence(0)
	public AvatarPropertiesRequestBackend_bAgentData bagentdata=new AvatarPropertiesRequestBackend_bAgentData();
	public AvatarPropertiesRequestBackend(){}
	public AvatarPropertiesRequestBackend(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
