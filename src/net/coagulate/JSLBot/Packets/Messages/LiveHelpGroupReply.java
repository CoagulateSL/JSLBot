package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class LiveHelpGroupReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 380; }
	@Nonnull
    public final String getName() { return "LiveHelpGroupReply"; }
	@Nonnull
    @Sequence(0)
	public LiveHelpGroupReply_bReplyData breplydata=new LiveHelpGroupReply_bReplyData();
}
