package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarInterestsReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 172; }
	public final String getName() { return "AvatarInterestsReply"; }
	@Sequence(0)
	public AvatarInterestsReply_bAgentData bagentdata=new AvatarInterestsReply_bAgentData();
	@Sequence(1)
	public AvatarInterestsReply_bPropertiesData bpropertiesdata=new AvatarInterestsReply_bPropertiesData();
	public AvatarInterestsReply(){}
	public AvatarInterestsReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
