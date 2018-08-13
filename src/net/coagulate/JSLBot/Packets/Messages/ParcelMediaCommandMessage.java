package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelMediaCommandMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 419; }
	public final String getName() { return "ParcelMediaCommandMessage"; }
	@Sequence(0)
	public ParcelMediaCommandMessage_bCommandBlock bcommandblock=new ParcelMediaCommandMessage_bCommandBlock();
}
