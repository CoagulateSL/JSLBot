package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RegionIDAndHandleReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 310; }
	public final String getName() { return "RegionIDAndHandleReply"; }
	@Sequence(0)
	public RegionIDAndHandleReply_bReplyBlock breplyblock=new RegionIDAndHandleReply_bReplyBlock();
}
