package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelMediaUpdate_bDataBlock extends Block {
	@Sequence(0)
	public Variable1 vmediaurl=new Variable1();
	@Sequence(1)
	public LLUUID vmediaid=new LLUUID();
	@Sequence(2)
	public U8 vmediaautoscale=new U8();
}
