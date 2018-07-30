package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DataServerLogout_bUserData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public IPADDR vviewerip=new IPADDR();
	@Sequence(2)
	public BOOL vdisconnect=new BOOL();
	@Sequence(3)
	public LLUUID vsessionid=new LLUUID();
}
