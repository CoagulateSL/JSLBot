package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelMediaUpdate_bDataBlockExtended extends Block {
	@Sequence(0)
	public Variable1 vmediatype=new Variable1();
	@Sequence(1)
	public Variable1 vmediadesc=new Variable1();
	@Sequence(2)
	public S32 vmediawidth=new S32();
	@Sequence(3)
	public S32 vmediaheight=new S32();
	@Sequence(4)
	public U8 vmedialoop=new U8();
}
