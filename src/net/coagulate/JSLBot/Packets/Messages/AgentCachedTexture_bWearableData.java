package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentCachedTexture_bWearableData extends Block {
	@Sequence(0)
	public LLUUID vid=new LLUUID();
	@Sequence(1)
	public U8 vtextureindex=new U8();
}
