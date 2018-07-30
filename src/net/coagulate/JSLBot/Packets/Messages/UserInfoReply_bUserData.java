package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UserInfoReply_bUserData extends Block {
	@Sequence(0)
	public BOOL vimviaemail=new BOOL();
	@Sequence(1)
	public Variable1 vdirectoryvisibility=new Variable1();
	@Sequence(2)
	public Variable2 vemail=new Variable2();
}
