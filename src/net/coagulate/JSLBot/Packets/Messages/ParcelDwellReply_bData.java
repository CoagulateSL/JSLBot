package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelDwellReply_bData extends Block {
	@Sequence(0)
	public S32 vlocalid=new S32();
	@Sequence(1)
	public LLUUID vparcelid=new LLUUID();
	@Sequence(2)
	public F32 vdwell=new F32();
}
