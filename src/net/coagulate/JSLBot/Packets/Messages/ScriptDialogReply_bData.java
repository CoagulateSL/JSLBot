package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptDialogReply_bData extends Block {
	@Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Sequence(1)
	public S32 vchatchannel=new S32();
	@Sequence(2)
	public S32 vbuttonindex=new S32();
	@Sequence(3)
	public Variable1 vbuttonlabel=new Variable1();
}
