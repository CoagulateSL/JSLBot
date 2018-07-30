package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class StartAuction_bParcelData extends Block {
	@Sequence(0)
	public LLUUID vparcelid=new LLUUID();
	@Sequence(1)
	public LLUUID vsnapshotid=new LLUUID();
	@Sequence(2)
	public Variable1 vname=new Variable1();
}
