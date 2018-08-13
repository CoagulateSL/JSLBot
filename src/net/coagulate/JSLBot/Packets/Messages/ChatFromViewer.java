package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ChatFromViewer extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 80; }
	public final String getName() { return "ChatFromViewer"; }
	@Sequence(0)
	public ChatFromViewer_bAgentData bagentdata=new ChatFromViewer_bAgentData();
	@Sequence(1)
	public ChatFromViewer_bChatData bchatdata=new ChatFromViewer_bChatData();
	public ChatFromViewer(){}
	public ChatFromViewer(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
