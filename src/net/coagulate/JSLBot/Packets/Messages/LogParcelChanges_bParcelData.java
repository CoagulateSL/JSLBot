package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LogParcelChanges_bParcelData extends Block {
	@Sequence(0)
	public LLUUID vparcelid=new LLUUID();
	@Sequence(1)
	public LLUUID vownerid=new LLUUID();
	@Sequence(2)
	public BOOL visownergroup=new BOOL();
	@Sequence(3)
	public S32 vactualarea=new S32();
	@Sequence(4)
	public S8 vaction=new S8();
	@Sequence(5)
	public LLUUID vtransactionid=new LLUUID();
}
