package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AvatarPickerRequestBackend extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 27; }
	@Nonnull
    public final String getName() { return "AvatarPickerRequestBackend"; }
	@Nonnull
    @Sequence(0)
	public AvatarPickerRequestBackend_bAgentData bagentdata=new AvatarPickerRequestBackend_bAgentData();
	@Nonnull
    @Sequence(1)
	public AvatarPickerRequestBackend_bData bdata=new AvatarPickerRequestBackend_bData();
	public AvatarPickerRequestBackend(){}
	public AvatarPickerRequestBackend(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
