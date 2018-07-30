package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateSimulator_bSimulatorInfo extends Block {
	@Sequence(0)
	public LLUUID vregionid=new LLUUID();
	@Sequence(1)
	public Variable1 vsimname=new Variable1();
	@Sequence(2)
	public U32 vestateid=new U32();
	@Sequence(3)
	public U8 vsimaccess=new U8();
}
