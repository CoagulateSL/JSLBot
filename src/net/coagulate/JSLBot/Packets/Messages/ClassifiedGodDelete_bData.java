package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ClassifiedGodDelete_bData extends Block {
	@Sequence(0)
	public LLUUID vclassifiedid=new LLUUID();
	@Sequence(1)
	public LLUUID vqueryid=new LLUUID();
}
