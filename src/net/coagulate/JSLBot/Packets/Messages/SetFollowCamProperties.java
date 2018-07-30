package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SetFollowCamProperties extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 159; }
	public final String getName() { return "SetFollowCamProperties"; }
	@Sequence(0)
	public SetFollowCamProperties_bObjectData bobjectdata=new SetFollowCamProperties_bObjectData();
	@Sequence(1)
	public List<SetFollowCamProperties_bCameraProperty> bcameraproperty;
}
