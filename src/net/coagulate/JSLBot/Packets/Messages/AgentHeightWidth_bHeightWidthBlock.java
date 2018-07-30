package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentHeightWidth_bHeightWidthBlock extends Block {
	@Sequence(0)
	public U32 vgencounter=new U32();
	@Sequence(1)
	public U16 vheight=new U16();
	@Sequence(2)
	public U16 vwidth=new U16();
}
