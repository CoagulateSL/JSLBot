package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectGrabUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 118; }
	@Nonnull
    public final String getName() { return "ObjectGrabUpdate"; }
	@Nonnull
    @Sequence(0)
	public ObjectGrabUpdate_bAgentData bagentdata=new ObjectGrabUpdate_bAgentData();
	@Nonnull
    @Sequence(1)
	public ObjectGrabUpdate_bObjectData bobjectdata=new ObjectGrabUpdate_bObjectData();
	@Sequence(2)
	public List<ObjectGrabUpdate_bSurfaceInfo> bsurfaceinfo;
	public ObjectGrabUpdate(){}
	public ObjectGrabUpdate(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
