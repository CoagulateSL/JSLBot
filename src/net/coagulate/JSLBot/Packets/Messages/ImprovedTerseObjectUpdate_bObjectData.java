package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ImprovedTerseObjectUpdate_bObjectData extends Block {
	@Sequence(0)
	public Variable1 vdata=new Variable1();
	@Sequence(1)
	public Variable2 vtextureentry=new Variable2();
}
