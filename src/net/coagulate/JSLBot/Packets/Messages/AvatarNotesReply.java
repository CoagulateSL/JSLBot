package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarNotesReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 176; }
	public final String getName() { return "AvatarNotesReply"; }
	@Sequence(0)
	public AvatarNotesReply_bAgentData bagentdata=new AvatarNotesReply_bAgentData();
	@Sequence(1)
	public AvatarNotesReply_bData bdata=new AvatarNotesReply_bData();
	public AvatarNotesReply(){}
	public AvatarNotesReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
