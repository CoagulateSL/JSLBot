package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class FeatureDisabled extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 19; }
	@Nonnull
    public final String getName() { return "FeatureDisabled"; }
	@Nonnull
    @Sequence(0)
	public FeatureDisabled_bFailureInfo bfailureinfo=new FeatureDisabled_bFailureInfo();
}
