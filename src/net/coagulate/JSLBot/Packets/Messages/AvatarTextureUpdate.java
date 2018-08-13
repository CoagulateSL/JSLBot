package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarTextureUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 4; }
	public final String getName() { return "AvatarTextureUpdate"; }
	@Sequence(0)
	public AvatarTextureUpdate_bAgentData bagentdata=new AvatarTextureUpdate_bAgentData();
	@Sequence(1)
	public List<AvatarTextureUpdate_bWearableData> bwearabledata;
	@Sequence(2)
	public List<AvatarTextureUpdate_bTextureData> btexturedata;
	public AvatarTextureUpdate(){}
	public AvatarTextureUpdate(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
