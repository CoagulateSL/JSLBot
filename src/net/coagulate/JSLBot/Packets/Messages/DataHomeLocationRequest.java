package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class DataHomeLocationRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 67; }
	@Nonnull
    public final String getName() { return "DataHomeLocationRequest"; }
	@Nonnull
    @Sequence(0)
	public DataHomeLocationRequest_bInfo binfo=new DataHomeLocationRequest_bInfo();
	@Nonnull
    @Sequence(1)
	public DataHomeLocationRequest_bAgentInfo bagentinfo=new DataHomeLocationRequest_bAgentInfo();
}
