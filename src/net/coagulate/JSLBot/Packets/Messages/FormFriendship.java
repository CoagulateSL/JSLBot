package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class FormFriendship extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 299; }
	@Nonnull
    public final String getName() { return "FormFriendship"; }
	@Nonnull
    @Sequence(0)
	public FormFriendship_bAgentBlock bagentblock=new FormFriendship_bAgentBlock();
}
