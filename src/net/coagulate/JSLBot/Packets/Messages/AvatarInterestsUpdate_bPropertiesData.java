package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarInterestsUpdate_bPropertiesData extends Block {
	@Sequence(0)
	public U32 vwanttomask=new U32();
	@Sequence(1)
	public Variable1 vwanttotext=new Variable1();
	@Sequence(2)
	public U32 vskillsmask=new U32();
	@Sequence(3)
	public Variable1 vskillstext=new Variable1();
	@Sequence(4)
	public Variable1 vlanguagestext=new Variable1();
}
