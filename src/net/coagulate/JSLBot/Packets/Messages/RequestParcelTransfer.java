package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RequestParcelTransfer extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 220; }
	@Nonnull
    public final String getName() { return "RequestParcelTransfer"; }
	@Nonnull
    @Sequence(0)
	public RequestParcelTransfer_bData bdata=new RequestParcelTransfer_bData();
	@Nonnull
    @Sequence(1)
	public RequestParcelTransfer_bRegionData bregiondata=new RequestParcelTransfer_bRegionData();
}
