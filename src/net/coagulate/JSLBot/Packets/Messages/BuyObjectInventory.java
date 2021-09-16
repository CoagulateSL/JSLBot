package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class BuyObjectInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 103; }
	@Nonnull
    public final String getName() { return "BuyObjectInventory"; }
	@Nonnull
    @Sequence(0)
	public BuyObjectInventory_bAgentData bagentdata=new BuyObjectInventory_bAgentData();
	@Nonnull
    @Sequence(1)
	public BuyObjectInventory_bData bdata=new BuyObjectInventory_bData();
	public BuyObjectInventory(){}
	public BuyObjectInventory(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
