package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestGodlikePowers extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 257; }
	public final String getName() { return "RequestGodlikePowers"; }
	@Sequence(0)
	public RequestGodlikePowers_bAgentData bagentdata=new RequestGodlikePowers_bAgentData();
	@Sequence(1)
	public RequestGodlikePowers_bRequestBlock brequestblock=new RequestGodlikePowers_bRequestBlock();
}
