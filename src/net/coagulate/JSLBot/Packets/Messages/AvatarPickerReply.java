package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class AvatarPickerReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 28; }
	@Nonnull
    public final String getName() { return "AvatarPickerReply"; }
	@Nonnull
    @Sequence(0)
	public AvatarPickerReply_bAgentData bagentdata=new AvatarPickerReply_bAgentData();
	@Sequence(1)
	public List<AvatarPickerReply_bData> bdata;
	public AvatarPickerReply(){}
	public AvatarPickerReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
