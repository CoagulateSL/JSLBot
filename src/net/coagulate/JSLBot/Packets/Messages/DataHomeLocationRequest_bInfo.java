package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DataHomeLocationRequest_bInfo extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public U32 vkickedfromestateid=new U32();
}
