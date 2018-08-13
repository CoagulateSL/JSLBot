package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ChatPass extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 239; }
	public final String getName() { return "ChatPass"; }
	@Sequence(0)
	public ChatPass_bChatData bchatdata=new ChatPass_bChatData();
}
