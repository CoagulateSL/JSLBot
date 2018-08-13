package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GrantGodlikePowers extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 258; }
	public final String getName() { return "GrantGodlikePowers"; }
	@Sequence(0)
	public GrantGodlikePowers_bAgentData bagentdata=new GrantGodlikePowers_bAgentData();
	@Sequence(1)
	public GrantGodlikePowers_bGrantData bgrantdata=new GrantGodlikePowers_bGrantData();
	public GrantGodlikePowers(){}
	public GrantGodlikePowers(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
