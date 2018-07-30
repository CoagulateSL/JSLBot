package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimStatus_bSimStatus extends Block {
	@Sequence(0)
	public BOOL vcanacceptagents=new BOOL();
	@Sequence(1)
	public BOOL vcanaccepttasks=new BOOL();
}
