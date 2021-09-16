package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ParcelAccessListReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 216; }
	@Nonnull
    public final String getName() { return "ParcelAccessListReply"; }
	@Nonnull
    @Sequence(0)
	public ParcelAccessListReply_bData bdata=new ParcelAccessListReply_bData();
	@Sequence(1)
	public List<ParcelAccessListReply_bList> blist;
}
