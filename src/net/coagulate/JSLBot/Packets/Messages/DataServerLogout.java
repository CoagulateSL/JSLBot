package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DataServerLogout extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 251; }
	public final String getName() { return "DataServerLogout"; }
	@Sequence(0)
	public DataServerLogout_bUserData buserdata=new DataServerLogout_bUserData();
}
