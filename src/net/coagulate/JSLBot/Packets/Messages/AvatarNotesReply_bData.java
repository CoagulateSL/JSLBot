package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarNotesReply_bData extends Block {
	@Sequence(0)
	public LLUUID vtargetid=new LLUUID();
	@Sequence(1)
	public Variable2 vnotes=new Variable2();
}
