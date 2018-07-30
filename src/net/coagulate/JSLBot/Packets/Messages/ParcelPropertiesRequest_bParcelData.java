package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelPropertiesRequest_bParcelData extends Block {
	@Sequence(0)
	public S32 vsequenceid=new S32();
	@Sequence(1)
	public F32 vwest=new F32();
	@Sequence(2)
	public F32 vsouth=new F32();
	@Sequence(3)
	public F32 veast=new F32();
	@Sequence(4)
	public F32 vnorth=new F32();
	@Sequence(5)
	public BOOL vsnapselection=new BOOL();
}
