package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelOverlay_bParcelData extends Block {
	@Sequence(0)
	public S32 vsequenceid=new S32();
	@Sequence(1)
	public Variable2 vdata=new Variable2();
}
