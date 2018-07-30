package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirFindQueryBackend_bAgentData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
}
