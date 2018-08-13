package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SendXferPacket extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 18; }
	public final String getName() { return "SendXferPacket"; }
	@Sequence(0)
	public SendXferPacket_bXferID bxferid=new SendXferPacket_bXferID();
	@Sequence(1)
	public SendXferPacket_bDataPacket bdatapacket=new SendXferPacket_bDataPacket();
}
