package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AvatarPickerRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 26; }
	@Nonnull
    public final String getName() { return "AvatarPickerRequest"; }
	@Nonnull
    @Sequence(0)
	public AvatarPickerRequest_bAgentData bagentdata=new AvatarPickerRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public AvatarPickerRequest_bData bdata=new AvatarPickerRequest_bData();
	public AvatarPickerRequest(){}
	public AvatarPickerRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
