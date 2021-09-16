package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ChatFromSimulator extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 139; }
	@Nonnull
    public final String getName() { return "ChatFromSimulator"; }
	@Nonnull
    @Sequence(0)
	public ChatFromSimulator_bChatData bchatdata=new ChatFromSimulator_bChatData();
}
