package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ActivateGestures extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 316; }
	@Nonnull
    public final String getName() { return "ActivateGestures"; }
	@Nonnull
    @Sequence(0)
	public ActivateGestures_bAgentData bagentdata=new ActivateGestures_bAgentData();
	@Sequence(1)
	public List<ActivateGestures_bData> bdata;
	public ActivateGestures(){}
	public ActivateGestures(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
