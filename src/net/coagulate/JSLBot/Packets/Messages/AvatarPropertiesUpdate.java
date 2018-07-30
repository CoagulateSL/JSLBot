package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarPropertiesUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 174; }
	public final String getName() { return "AvatarPropertiesUpdate"; }
	@Sequence(0)
	public AvatarPropertiesUpdate_bAgentData bagentdata=new AvatarPropertiesUpdate_bAgentData();
	@Sequence(1)
	public AvatarPropertiesUpdate_bPropertiesData bpropertiesdata=new AvatarPropertiesUpdate_bPropertiesData();
}
