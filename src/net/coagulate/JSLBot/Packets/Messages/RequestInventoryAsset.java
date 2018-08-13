package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestInventoryAsset extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 282; }
	public final String getName() { return "RequestInventoryAsset"; }
	@Sequence(0)
	public RequestInventoryAsset_bQueryData bquerydata=new RequestInventoryAsset_bQueryData();
}
