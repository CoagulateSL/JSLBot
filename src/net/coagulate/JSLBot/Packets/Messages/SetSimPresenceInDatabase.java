package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class SetSimPresenceInDatabase extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 23; }
	@Nonnull
    public final String getName() { return "SetSimPresenceInDatabase"; }
	@Nonnull
    @Sequence(0)
	public SetSimPresenceInDatabase_bSimData bsimdata=new SetSimPresenceInDatabase_bSimData();
}
