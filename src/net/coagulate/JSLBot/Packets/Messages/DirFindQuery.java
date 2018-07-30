package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirFindQuery extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 31; }
	public final String getName() { return "DirFindQuery"; }
	@Sequence(0)
	public DirFindQuery_bAgentData bagentdata=new DirFindQuery_bAgentData();
	@Sequence(1)
	public DirFindQuery_bQueryData bquerydata=new DirFindQuery_bQueryData();
}
