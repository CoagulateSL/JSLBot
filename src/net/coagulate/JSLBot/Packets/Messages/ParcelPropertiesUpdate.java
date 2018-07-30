package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelPropertiesUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 198; }
	public final String getName() { return "ParcelPropertiesUpdate"; }
	@Sequence(0)
	public ParcelPropertiesUpdate_bAgentData bagentdata=new ParcelPropertiesUpdate_bAgentData();
	@Sequence(1)
	public ParcelPropertiesUpdate_bParcelData bparceldata=new ParcelPropertiesUpdate_bParcelData();
}
