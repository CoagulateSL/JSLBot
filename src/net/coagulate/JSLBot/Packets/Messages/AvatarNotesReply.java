package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AvatarNotesReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 176; }
	@Nonnull
    public final String getName() { return "AvatarNotesReply"; }
	@Nonnull
    @Sequence(0)
	public AvatarNotesReply_bAgentData bagentdata=new AvatarNotesReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public AvatarNotesReply_bData bdata=new AvatarNotesReply_bData();
	public AvatarNotesReply(){}
	public AvatarNotesReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
