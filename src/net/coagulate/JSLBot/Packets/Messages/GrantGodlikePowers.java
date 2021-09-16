package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GrantGodlikePowers extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 258; }
	@Nonnull
    public final String getName() { return "GrantGodlikePowers"; }
	@Nonnull
    @Sequence(0)
	public GrantGodlikePowers_bAgentData bagentdata=new GrantGodlikePowers_bAgentData();
	@Nonnull
    @Sequence(1)
	public GrantGodlikePowers_bGrantData bgrantdata=new GrantGodlikePowers_bGrantData();
	public GrantGodlikePowers(){}
	public GrantGodlikePowers(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
