package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarPickerRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 26; }
	public final String getName() { return "AvatarPickerRequest"; }
	@Sequence(0)
	public AvatarPickerRequest_bAgentData bagentdata=new AvatarPickerRequest_bAgentData();
	@Sequence(1)
	public AvatarPickerRequest_bData bdata=new AvatarPickerRequest_bData();
	public AvatarPickerRequest(){}
	public AvatarPickerRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
