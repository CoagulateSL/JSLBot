package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectCategory_bObjectData extends Block {
	@Sequence(0)
	public U32 vlocalid=new U32();
	@Sequence(1)
	public U32 vcategory=new U32();
}
