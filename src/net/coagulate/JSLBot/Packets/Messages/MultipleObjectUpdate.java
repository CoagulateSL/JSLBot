package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class MultipleObjectUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 2; }
	@Nonnull
    public final String getName() { return "MultipleObjectUpdate"; }
	@Nonnull
    @Sequence(0)
	public MultipleObjectUpdate_bAgentData bagentdata=new MultipleObjectUpdate_bAgentData();
	@Sequence(1)
	public List<MultipleObjectUpdate_bObjectData> bobjectdata;
	public MultipleObjectUpdate(){}
	public MultipleObjectUpdate(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
