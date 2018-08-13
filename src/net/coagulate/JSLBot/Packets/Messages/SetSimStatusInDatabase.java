package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SetSimStatusInDatabase extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 22; }
	public final String getName() { return "SetSimStatusInDatabase"; }
	@Sequence(0)
	public SetSimStatusInDatabase_bData bdata=new SetSimStatusInDatabase_bData();
}
