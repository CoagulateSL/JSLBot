package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectPermissions extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 105; }
	@Nonnull
    public final String getName() { return "ObjectPermissions"; }
	@Nonnull
    @Sequence(0)
	public ObjectPermissions_bAgentData bagentdata=new ObjectPermissions_bAgentData();
	@Nonnull
    @Sequence(1)
	public ObjectPermissions_bHeaderData bheaderdata=new ObjectPermissions_bHeaderData();
	@Sequence(2)
	public List<ObjectPermissions_bObjectData> bobjectdata;
	public ObjectPermissions(){}
	public ObjectPermissions(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
