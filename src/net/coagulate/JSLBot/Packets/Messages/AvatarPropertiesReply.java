package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarPropertiesReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 171; }
	public final String getName() { return "AvatarPropertiesReply"; }
	@Sequence(0)
	public AvatarPropertiesReply_bAgentData bagentdata=new AvatarPropertiesReply_bAgentData();
	@Sequence(1)
	public AvatarPropertiesReply_bPropertiesData bpropertiesdata=new AvatarPropertiesReply_bPropertiesData();
	public AvatarPropertiesReply(){}
	public AvatarPropertiesReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
