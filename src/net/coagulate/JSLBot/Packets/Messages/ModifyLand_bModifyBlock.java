package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ModifyLand_bModifyBlock extends Block {
	@Sequence(0)
	public U8 vaction=new U8();
	@Sequence(1)
	public U8 vbrushsize=new U8();
	@Sequence(2)
	public F32 vseconds=new F32();
	@Sequence(3)
	public F32 vheight=new F32();
}
