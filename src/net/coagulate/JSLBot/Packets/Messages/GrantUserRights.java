package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class GrantUserRights extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 320; }
	@Nonnull
    public final String getName() { return "GrantUserRights"; }
	@Nonnull
    @Sequence(0)
	public GrantUserRights_bAgentData bagentdata=new GrantUserRights_bAgentData();
	@Sequence(1)
	public List<GrantUserRights_bRights> brights;
	public GrantUserRights(){}
	public GrantUserRights(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
