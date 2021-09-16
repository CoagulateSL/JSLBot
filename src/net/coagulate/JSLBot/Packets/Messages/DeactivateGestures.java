package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class DeactivateGestures extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 317; }
	@Nonnull
    public final String getName() { return "DeactivateGestures"; }
	@Nonnull
    @Sequence(0)
	public DeactivateGestures_bAgentData bagentdata=new DeactivateGestures_bAgentData();
	@Sequence(1)
	public List<DeactivateGestures_bData> bdata;
	public DeactivateGestures(){}
	public DeactivateGestures(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
