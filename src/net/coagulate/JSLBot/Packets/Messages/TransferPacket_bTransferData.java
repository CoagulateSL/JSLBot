package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TransferPacket_bTransferData extends Block {
	@Sequence(0)
	public LLUUID vtransferid=new LLUUID();
	@Sequence(1)
	public S32 vchanneltype=new S32();
	@Sequence(2)
	public S32 vpacket=new S32();
	@Sequence(3)
	public S32 vstatus=new S32();
	@Sequence(4)
	public Variable2 vdata=new Variable2();
}
