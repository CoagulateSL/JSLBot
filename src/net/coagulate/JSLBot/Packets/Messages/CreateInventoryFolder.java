package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class CreateInventoryFolder extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 273; }
	@Nonnull
    public final String getName() { return "CreateInventoryFolder"; }
	@Nonnull
    @Sequence(0)
	public CreateInventoryFolder_bAgentData bagentdata=new CreateInventoryFolder_bAgentData();
	@Nonnull
    @Sequence(1)
	public CreateInventoryFolder_bFolderData bfolderdata=new CreateInventoryFolder_bFolderData();
	public CreateInventoryFolder(){}
	public CreateInventoryFolder(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
