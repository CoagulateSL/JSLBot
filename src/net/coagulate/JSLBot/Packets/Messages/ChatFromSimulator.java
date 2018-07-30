package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ChatFromSimulator extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 139; }
	public final String getName() { return "ChatFromSimulator"; }
	@Sequence(0)
	public ChatFromSimulator_bChatData bchatdata=new ChatFromSimulator_bChatData();
}
