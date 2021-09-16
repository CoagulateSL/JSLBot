package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class PickInfoUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 185; }
	@Nonnull
    public final String getName() { return "PickInfoUpdate"; }
	@Nonnull
    @Sequence(0)
	public PickInfoUpdate_bAgentData bagentdata=new PickInfoUpdate_bAgentData();
	@Nonnull
    @Sequence(1)
	public PickInfoUpdate_bData bdata=new PickInfoUpdate_bData();
	public PickInfoUpdate(){}
	public PickInfoUpdate(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
