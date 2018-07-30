package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TeleportFinish_bInfo extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public U32 vlocationid=new U32();
	@Sequence(2)
	public IPADDR vsimip=new IPADDR();
	@Sequence(3)
	public IPPORT vsimport=new IPPORT();
	@Sequence(4)
	public U64 vregionhandle=new U64();
	@Sequence(5)
	public Variable2 vseedcapability=new Variable2();
	@Sequence(6)
	public U8 vsimaccess=new U8();
	@Sequence(7)
	public U32 vteleportflags=new U32();
}
