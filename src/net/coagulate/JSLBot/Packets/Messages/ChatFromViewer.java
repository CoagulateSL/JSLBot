package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ChatFromViewer extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 80; }
	@Nonnull
    public final String getName() { return "ChatFromViewer"; }
	@Nonnull
    @Sequence(0)
	public ChatFromViewer_bAgentData bagentdata=new ChatFromViewer_bAgentData();
	@Nonnull
    @Sequence(1)
	public ChatFromViewer_bChatData bchatdata=new ChatFromViewer_bChatData();
	public ChatFromViewer(){}
	public ChatFromViewer(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
