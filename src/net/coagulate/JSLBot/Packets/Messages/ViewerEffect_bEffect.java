package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class ViewerEffect_bEffect extends Block {
	@Nonnull
	@Sequence(0)
	public LLUUID vid=new LLUUID();
	@Nonnull
	@Sequence(1)
	public LLUUID vagentid=new LLUUID();
	@Nonnull
	@Sequence(2)
	public U8 vtype=new U8();
	@Nonnull
	@Sequence(3)
	public F32 vduration=new F32();
	@Nonnull
	@Sequence(4)
	public Fixed4 vcolor=new Fixed4();
	@Nonnull
	@Sequence(5)
	public Variable1 vtypedata=new Variable1();
}
