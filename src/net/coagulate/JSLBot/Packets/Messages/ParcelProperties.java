package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelProperties extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 23; }
	@Nonnull
    public final String getName() { return "ParcelProperties"; }
	@Nonnull
    @Sequence(0)
	public ParcelProperties_bParcelData bparceldata=new ParcelProperties_bParcelData();
	@Nonnull
    @Sequence(1)
	public ParcelProperties_bAgeVerificationBlock bageverificationblock=new ParcelProperties_bAgeVerificationBlock();
	@Nonnull
    @Sequence(2)
	public ParcelProperties_bRegionAllowAccessBlock bregionallowaccessblock=new ParcelProperties_bRegionAllowAccessBlock();
}
