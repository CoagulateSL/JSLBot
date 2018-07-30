package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TransferAbort_bTransferInfo extends Block {
	@Sequence(0)
	public LLUUID vtransferid=new LLUUID();
	@Sequence(1)
	public S32 vchanneltype=new S32();
}
