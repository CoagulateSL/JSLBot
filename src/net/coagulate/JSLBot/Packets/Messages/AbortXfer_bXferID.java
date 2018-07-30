package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AbortXfer_bXferID extends Block {
	@Sequence(0)
	public U64 vid=new U64();
	@Sequence(1)
	public S32 vresult=new S32();
}
