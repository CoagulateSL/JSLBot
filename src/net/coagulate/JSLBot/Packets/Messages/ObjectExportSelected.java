package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectExportSelected extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 123; }
	@Nonnull
    public final String getName() { return "ObjectExportSelected"; }
	@Nonnull
    @Sequence(0)
	public ObjectExportSelected_bAgentData bagentdata=new ObjectExportSelected_bAgentData();
	@Sequence(1)
	public List<ObjectExportSelected_bObjectData> bobjectdata;
	public ObjectExportSelected(){}
	public ObjectExportSelected(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
