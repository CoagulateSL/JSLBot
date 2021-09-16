package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RezScript extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 304; }
	@Nonnull
    public final String getName() { return "RezScript"; }
	@Nonnull
    @Sequence(0)
	public RezScript_bAgentData bagentdata=new RezScript_bAgentData();
	@Nonnull
    @Sequence(1)
	public RezScript_bUpdateBlock bupdateblock=new RezScript_bUpdateBlock();
	@Nonnull
    @Sequence(2)
	public RezScript_bInventoryBlock binventoryblock=new RezScript_bInventoryBlock();
	public RezScript(){}
	public RezScript(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
