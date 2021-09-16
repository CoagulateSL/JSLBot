package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ViewerStartAuction extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 228; }
	@Nonnull
    public final String getName() { return "ViewerStartAuction"; }
	@Nonnull
    @Sequence(0)
	public ViewerStartAuction_bAgentData bagentdata=new ViewerStartAuction_bAgentData();
	@Nonnull
    @Sequence(1)
	public ViewerStartAuction_bParcelData bparceldata=new ViewerStartAuction_bParcelData();
	public ViewerStartAuction(){}
	public ViewerStartAuction(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
