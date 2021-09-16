package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ChangeUserRights extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 321; }
	@Nonnull
    public final String getName() { return "ChangeUserRights"; }
	@Nonnull
    @Sequence(0)
	public ChangeUserRights_bAgentData bagentdata=new ChangeUserRights_bAgentData();
	@Sequence(1)
	public List<ChangeUserRights_bRights> brights;
	public ChangeUserRights(){}
	public ChangeUserRights(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
