package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RegionHandshake_bRegionInfo3 extends Block {
	@Sequence(0)
	public S32 vcpuclassid=new S32();
	@Sequence(1)
	public S32 vcpuratio=new S32();
	@Sequence(2)
	public Variable1 vcoloname=new Variable1();
	@Sequence(3)
	public Variable1 vproductsku=new Variable1();
	@Sequence(4)
	public Variable1 vproductname=new Variable1();
}
