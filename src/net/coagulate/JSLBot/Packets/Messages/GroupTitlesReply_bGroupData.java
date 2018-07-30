package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupTitlesReply_bGroupData extends Block {
	@Sequence(0)
	public Variable1 vtitle=new Variable1();
	@Sequence(1)
	public LLUUID vroleid=new LLUUID();
	@Sequence(2)
	public BOOL vselected=new BOOL();
}
