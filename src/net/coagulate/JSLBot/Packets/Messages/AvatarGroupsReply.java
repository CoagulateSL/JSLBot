package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class AvatarGroupsReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 173; }
	@Nonnull
    public final String getName() { return "AvatarGroupsReply"; }
	@Nonnull
    @Sequence(0)
	public AvatarGroupsReply_bAgentData bagentdata=new AvatarGroupsReply_bAgentData();
	@Sequence(1)
	public List<AvatarGroupsReply_bGroupData> bgroupdata;
	@Nonnull
    @Sequence(2)
	public AvatarGroupsReply_bNewGroupData bnewgroupdata=new AvatarGroupsReply_bNewGroupData();
	public AvatarGroupsReply(){}
	public AvatarGroupsReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
