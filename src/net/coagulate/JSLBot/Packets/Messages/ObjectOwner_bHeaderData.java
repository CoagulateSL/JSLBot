package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectOwner_bHeaderData extends Block {
	@Sequence(0)
	public BOOL voverride=new BOOL();
	@Sequence(1)
	public LLUUID vownerid=new LLUUID();
	@Sequence(2)
	public LLUUID vgroupid=new LLUUID();
}
