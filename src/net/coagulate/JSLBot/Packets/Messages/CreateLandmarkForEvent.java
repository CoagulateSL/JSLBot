package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CreateLandmarkForEvent extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 306; }
	public final String getName() { return "CreateLandmarkForEvent"; }
	@Sequence(0)
	public CreateLandmarkForEvent_bAgentData bagentdata=new CreateLandmarkForEvent_bAgentData();
	@Sequence(1)
	public CreateLandmarkForEvent_bEventData beventdata=new CreateLandmarkForEvent_bEventData();
	@Sequence(2)
	public CreateLandmarkForEvent_bInventoryBlock binventoryblock=new CreateLandmarkForEvent_bInventoryBlock();
	public CreateLandmarkForEvent(){}
	public CreateLandmarkForEvent(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
