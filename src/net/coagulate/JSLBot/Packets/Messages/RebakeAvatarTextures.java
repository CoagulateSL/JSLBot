package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RebakeAvatarTextures extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 87; }
	public final String getName() { return "RebakeAvatarTextures"; }
	@Sequence(0)
	public RebakeAvatarTextures_bTextureData btexturedata=new RebakeAvatarTextures_bTextureData();
}
