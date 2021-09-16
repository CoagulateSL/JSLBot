package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class SystemMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 404; }
	@Nonnull
    public final String getName() { return "SystemMessage"; }
	@Nonnull
    @Sequence(0)
	public SystemMessage_bMethodData bmethoddata=new SystemMessage_bMethodData();
	@Sequence(1)
	public List<SystemMessage_bParamList> bparamlist;
}
