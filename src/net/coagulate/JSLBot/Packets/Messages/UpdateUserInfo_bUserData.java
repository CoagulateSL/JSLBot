package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateUserInfo_bUserData extends Block {
	@Sequence(0)
	public BOOL vimviaemail=new BOOL();
	@Sequence(1)
	public Variable1 vdirectoryvisibility=new Variable1();
}
