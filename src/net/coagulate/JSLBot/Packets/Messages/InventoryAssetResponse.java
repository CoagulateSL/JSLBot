package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class InventoryAssetResponse extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 283; }
	public final String getName() { return "InventoryAssetResponse"; }
	@Sequence(0)
	public InventoryAssetResponse_bQueryData bquerydata=new InventoryAssetResponse_bQueryData();
}
