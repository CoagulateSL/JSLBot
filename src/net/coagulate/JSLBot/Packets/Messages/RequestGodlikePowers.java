package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RequestGodlikePowers extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 257; }
	@Nonnull
    public final String getName() { return "RequestGodlikePowers"; }
	@Nonnull
    @Sequence(0)
	public RequestGodlikePowers_bAgentData bagentdata=new RequestGodlikePowers_bAgentData();
	@Nonnull
    @Sequence(1)
	public RequestGodlikePowers_bRequestBlock brequestblock=new RequestGodlikePowers_bRequestBlock();
	public RequestGodlikePowers(){}
	public RequestGodlikePowers(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
