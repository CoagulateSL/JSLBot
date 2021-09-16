package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RegionHandleRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 309; }
	@Nonnull
    public final String getName() { return "RegionHandleRequest"; }
	@Nonnull
    @Sequence(0)
	public RegionHandleRequest_bRequestBlock brequestblock=new RegionHandleRequest_bRequestBlock();
}
