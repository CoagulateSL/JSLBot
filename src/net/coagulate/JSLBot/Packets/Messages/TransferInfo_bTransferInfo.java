package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TransferInfo_bTransferInfo extends Block {
	@Sequence(0)
	public LLUUID vtransferid=new LLUUID();
	@Sequence(1)
	public S32 vchanneltype=new S32();
	@Sequence(2)
	public S32 vtargettype=new S32();
	@Sequence(3)
	public S32 vstatus=new S32();
	@Sequence(4)
	public S32 vsize=new S32();
	@Sequence(5)
	public Variable2 vparams=new Variable2();
}
