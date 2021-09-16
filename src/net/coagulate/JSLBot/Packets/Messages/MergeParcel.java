package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class MergeParcel extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 223; }
	@Nonnull
    public final String getName() { return "MergeParcel"; }
	@Nonnull
    @Sequence(0)
	public MergeParcel_bMasterParcelData bmasterparceldata=new MergeParcel_bMasterParcelData();
	@Sequence(1)
	public List<MergeParcel_bSlaveParcelData> bslaveparceldata;
}
