package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelMediaCommandMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 419; }
	@Nonnull
    public final String getName() { return "ParcelMediaCommandMessage"; }
	@Nonnull
    @Sequence(0)
	public ParcelMediaCommandMessage_bCommandBlock bcommandblock=new ParcelMediaCommandMessage_bCommandBlock();
}
