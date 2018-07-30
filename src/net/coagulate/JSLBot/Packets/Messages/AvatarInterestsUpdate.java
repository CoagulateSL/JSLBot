package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarInterestsUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 175; }
	public final String getName() { return "AvatarInterestsUpdate"; }
	@Sequence(0)
	public AvatarInterestsUpdate_bAgentData bagentdata=new AvatarInterestsUpdate_bAgentData();
	@Sequence(1)
	public AvatarInterestsUpdate_bPropertiesData bpropertiesdata=new AvatarInterestsUpdate_bPropertiesData();
}
