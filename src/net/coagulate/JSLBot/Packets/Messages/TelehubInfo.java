package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class TelehubInfo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 10; }
	@Nonnull
    public final String getName() { return "TelehubInfo"; }
	@Nonnull
    @Sequence(0)
	public TelehubInfo_bTelehubBlock btelehubblock=new TelehubInfo_bTelehubBlock();
	@Sequence(1)
	public List<TelehubInfo_bSpawnPointBlock> bspawnpointblock;
}
