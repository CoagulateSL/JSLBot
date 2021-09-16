package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ViewerEffect extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 17; }
	@Nonnull
    public final String getName() { return "ViewerEffect"; }
	@Nonnull
    @Sequence(0)
	public ViewerEffect_bAgentData bagentdata=new ViewerEffect_bAgentData();
	@Sequence(1)
	public List<ViewerEffect_bEffect> beffect;
	public ViewerEffect(){}
	public ViewerEffect(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
