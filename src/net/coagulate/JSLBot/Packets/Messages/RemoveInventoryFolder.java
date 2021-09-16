package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class RemoveInventoryFolder extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 276; }
	@Nonnull
    public final String getName() { return "RemoveInventoryFolder"; }
	@Nonnull
    @Sequence(0)
	public RemoveInventoryFolder_bAgentData bagentdata=new RemoveInventoryFolder_bAgentData();
	@Sequence(1)
	public List<RemoveInventoryFolder_bFolderData> bfolderdata;
	public RemoveInventoryFolder(){}
	public RemoveInventoryFolder(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
