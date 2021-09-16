package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class SetSimStatusInDatabase extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 22; }
	@Nonnull
    public final String getName() { return "SetSimStatusInDatabase"; }
	@Nonnull
    @Sequence(0)
	public SetSimStatusInDatabase_bData bdata=new SetSimStatusInDatabase_bData();
}
