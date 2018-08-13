package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelInfoReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 55; }
	public final String getName() { return "ParcelInfoReply"; }
	@Sequence(0)
	public ParcelInfoReply_bAgentData bagentdata=new ParcelInfoReply_bAgentData();
	@Sequence(1)
	public ParcelInfoReply_bData bdata=new ParcelInfoReply_bData();
	public ParcelInfoReply(){}
	public ParcelInfoReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
