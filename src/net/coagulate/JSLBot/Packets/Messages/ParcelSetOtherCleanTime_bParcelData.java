package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelSetOtherCleanTime_bParcelData extends Block {
	@Sequence(0)
	public S32 vlocalid=new S32();
	@Sequence(1)
	public S32 vothercleantime=new S32();
}
