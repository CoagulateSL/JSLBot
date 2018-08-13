package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ViewerFrozenMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 137; }
	public final String getName() { return "ViewerFrozenMessage"; }
	@Sequence(0)
	public ViewerFrozenMessage_bFrozenData bfrozendata=new ViewerFrozenMessage_bFrozenData();
}
