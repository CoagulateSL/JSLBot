package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GodKickUser_bUserInfo extends Block {
	@Sequence(0)
	public LLUUID vgodid=new LLUUID();
	@Sequence(1)
	public LLUUID vgodsessionid=new LLUUID();
	@Sequence(2)
	public LLUUID vagentid=new LLUUID();
	@Sequence(3)
	public U32 vkickflags=new U32();
	@Sequence(4)
	public Variable2 vreason=new Variable2();
}
