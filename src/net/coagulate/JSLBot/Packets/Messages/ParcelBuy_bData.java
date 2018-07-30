package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelBuy_bData extends Block {
	@Sequence(0)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(1)
	public BOOL visgroupowned=new BOOL();
	@Sequence(2)
	public BOOL vremovecontribution=new BOOL();
	@Sequence(3)
	public S32 vlocalid=new S32();
	@Sequence(4)
	public BOOL vfinal=new BOOL();
}
