package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarGroupsReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 173; }
	public final String getName() { return "AvatarGroupsReply"; }
	@Sequence(0)
	public AvatarGroupsReply_bAgentData bagentdata=new AvatarGroupsReply_bAgentData();
	@Sequence(1)
	public List<AvatarGroupsReply_bGroupData> bgroupdata;
	@Sequence(2)
	public AvatarGroupsReply_bNewGroupData bnewgroupdata=new AvatarGroupsReply_bNewGroupData();
	public AvatarGroupsReply(){}
	public AvatarGroupsReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
