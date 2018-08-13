package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TelehubInfo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 10; }
	public final String getName() { return "TelehubInfo"; }
	@Sequence(0)
	public TelehubInfo_bTelehubBlock btelehubblock=new TelehubInfo_bTelehubBlock();
	@Sequence(1)
	public List<TelehubInfo_bSpawnPointBlock> bspawnpointblock;
}
