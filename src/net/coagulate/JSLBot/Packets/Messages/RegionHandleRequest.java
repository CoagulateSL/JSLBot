package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RegionHandleRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 309; }
	public final String getName() { return "RegionHandleRequest"; }
	@Sequence(0)
	public RegionHandleRequest_bRequestBlock brequestblock=new RegionHandleRequest_bRequestBlock();
}
