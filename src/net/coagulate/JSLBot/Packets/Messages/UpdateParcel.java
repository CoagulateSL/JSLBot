package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateParcel extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 221; }
	public final String getName() { return "UpdateParcel"; }
	@Sequence(0)
	public UpdateParcel_bParcelData bparceldata=new UpdateParcel_bParcelData();
}
