package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelAccessListReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 216; }
	public final String getName() { return "ParcelAccessListReply"; }
	@Sequence(0)
	public ParcelAccessListReply_bData bdata=new ParcelAccessListReply_bData();
	@Sequence(1)
	public List<ParcelAccessListReply_bList> blist;
}
