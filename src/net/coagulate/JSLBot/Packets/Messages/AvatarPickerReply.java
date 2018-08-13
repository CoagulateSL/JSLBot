package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarPickerReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 28; }
	public final String getName() { return "AvatarPickerReply"; }
	@Sequence(0)
	public AvatarPickerReply_bAgentData bagentdata=new AvatarPickerReply_bAgentData();
	@Sequence(1)
	public List<AvatarPickerReply_bData> bdata;
	public AvatarPickerReply(){}
	public AvatarPickerReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
