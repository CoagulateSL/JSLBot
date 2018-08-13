package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarClassifiedReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 42; }
	public final String getName() { return "AvatarClassifiedReply"; }
	@Sequence(0)
	public AvatarClassifiedReply_bAgentData bagentdata=new AvatarClassifiedReply_bAgentData();
	@Sequence(1)
	public List<AvatarClassifiedReply_bData> bdata;
	public AvatarClassifiedReply(){}
	public AvatarClassifiedReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
