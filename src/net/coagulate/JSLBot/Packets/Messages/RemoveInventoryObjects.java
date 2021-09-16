package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class RemoveInventoryObjects extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 284; }
	@Nonnull
    public final String getName() { return "RemoveInventoryObjects"; }
	@Nonnull
    @Sequence(0)
	public RemoveInventoryObjects_bAgentData bagentdata=new RemoveInventoryObjects_bAgentData();
	@Sequence(1)
	public List<RemoveInventoryObjects_bFolderData> bfolderdata;
	@Sequence(2)
	public List<RemoveInventoryObjects_bItemData> bitemdata;
	public RemoveInventoryObjects(){}
	public RemoveInventoryObjects(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
