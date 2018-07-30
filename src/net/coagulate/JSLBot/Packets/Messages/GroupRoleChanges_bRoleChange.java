package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupRoleChanges_bRoleChange extends Block {
	@Sequence(0)
	public LLUUID vroleid=new LLUUID();
	@Sequence(1)
	public LLUUID vmemberid=new LLUUID();
	@Sequence(2)
	public U32 vchange=new U32();
}
