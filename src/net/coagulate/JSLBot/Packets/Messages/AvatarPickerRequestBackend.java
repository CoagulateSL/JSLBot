package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarPickerRequestBackend extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 27; }
	public final String getName() { return "AvatarPickerRequestBackend"; }
	@Sequence(0)
	public AvatarPickerRequestBackend_bAgentData bagentdata=new AvatarPickerRequestBackend_bAgentData();
	@Sequence(1)
	public AvatarPickerRequestBackend_bData bdata=new AvatarPickerRequestBackend_bData();
	public AvatarPickerRequestBackend(){}
	public AvatarPickerRequestBackend(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
