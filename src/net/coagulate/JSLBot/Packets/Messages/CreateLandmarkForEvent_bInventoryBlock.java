package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CreateLandmarkForEvent_bInventoryBlock extends Block {
	@Sequence(0)
	public LLUUID vfolderid=new LLUUID();
	@Sequence(1)
	public Variable1 vname=new Variable1();
}
