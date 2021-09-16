package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ObjectPropertiesFamily extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 10; }
	@Nonnull
    public final String getName() { return "ObjectPropertiesFamily"; }
	@Nonnull
    @Sequence(0)
	public ObjectPropertiesFamily_bObjectData bobjectdata=new ObjectPropertiesFamily_bObjectData();
}
