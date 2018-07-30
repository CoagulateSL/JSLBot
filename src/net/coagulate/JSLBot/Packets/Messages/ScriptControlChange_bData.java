package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptControlChange_bData extends Block {
	@Sequence(0)
	public BOOL vtakecontrols=new BOOL();
	@Sequence(1)
	public U32 vcontrols=new U32();
	@Sequence(2)
	public BOOL vpasstoagent=new BOOL();
}
