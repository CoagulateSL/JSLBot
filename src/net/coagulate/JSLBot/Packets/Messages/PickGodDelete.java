package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class PickGodDelete extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 187; }
	@Nonnull
    public final String getName() { return "PickGodDelete"; }
	@Nonnull
    @Sequence(0)
	public PickGodDelete_bAgentData bagentdata=new PickGodDelete_bAgentData();
	@Nonnull
    @Sequence(1)
	public PickGodDelete_bData bdata=new PickGodDelete_bData();
	public PickGodDelete(){}
	public PickGodDelete(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
