package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class StartLure_bTargetData extends Block {
	@Sequence(0)
	public LLUUID vtargetid=new LLUUID();
}
