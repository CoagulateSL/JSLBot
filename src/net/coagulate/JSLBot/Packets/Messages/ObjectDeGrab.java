package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectDeGrab extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 119; }
	@Nonnull
    public final String getName() { return "ObjectDeGrab"; }
	@Nonnull
    @Sequence(0)
	public ObjectDeGrab_bAgentData bagentdata=new ObjectDeGrab_bAgentData();
	@Nonnull
    @Sequence(1)
	public ObjectDeGrab_bObjectData bobjectdata=new ObjectDeGrab_bObjectData();
	@Sequence(2)
	public List<ObjectDeGrab_bSurfaceInfo> bsurfaceinfo;
	public ObjectDeGrab(){}
	public ObjectDeGrab(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
