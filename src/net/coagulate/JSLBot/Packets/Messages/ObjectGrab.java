package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectGrab extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 117; }
	@Nonnull
    public final String getName() { return "ObjectGrab"; }
	@Nonnull
    @Sequence(0)
	public ObjectGrab_bAgentData bagentdata=new ObjectGrab_bAgentData();
	@Nonnull
    @Sequence(1)
	public ObjectGrab_bObjectData bobjectdata=new ObjectGrab_bObjectData();
	@Sequence(2)
	public List<ObjectGrab_bSurfaceInfo> bsurfaceinfo;
	public ObjectGrab(){}
	public ObjectGrab(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
