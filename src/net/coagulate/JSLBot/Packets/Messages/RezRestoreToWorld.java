package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RezRestoreToWorld extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 425; }
	@Nonnull
    public final String getName() { return "RezRestoreToWorld"; }
	@Nonnull
    @Sequence(0)
	public RezRestoreToWorld_bAgentData bagentdata=new RezRestoreToWorld_bAgentData();
	@Nonnull
    @Sequence(1)
	public RezRestoreToWorld_bInventoryData binventorydata=new RezRestoreToWorld_bInventoryData();
	public RezRestoreToWorld(){}
	public RezRestoreToWorld(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
