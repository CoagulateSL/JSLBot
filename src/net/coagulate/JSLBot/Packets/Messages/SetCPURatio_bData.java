package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SetCPURatio_bData extends Block {
	@Sequence(0)
	public U8 vratio=new U8();
}
