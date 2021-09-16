package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class CreateLandmarkForEvent extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 306; }
	@Nonnull
    public final String getName() { return "CreateLandmarkForEvent"; }
	@Nonnull
    @Sequence(0)
	public CreateLandmarkForEvent_bAgentData bagentdata=new CreateLandmarkForEvent_bAgentData();
	@Nonnull
    @Sequence(1)
	public CreateLandmarkForEvent_bEventData beventdata=new CreateLandmarkForEvent_bEventData();
	@Nonnull
    @Sequence(2)
	public CreateLandmarkForEvent_bInventoryBlock binventoryblock=new CreateLandmarkForEvent_bInventoryBlock();
	public CreateLandmarkForEvent(){}
	public CreateLandmarkForEvent(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
