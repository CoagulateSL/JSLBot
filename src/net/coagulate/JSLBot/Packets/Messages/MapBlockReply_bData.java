package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class MapBlockReply_bData extends Block {
	@Nonnull
    @Sequence(0)
	public U16 vx=new U16();
	@Nonnull
    @Sequence(1)
	public U16 vy=new U16();
	@Nonnull
    @Sequence(2)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(3)
	public U8 vaccess=new U8();
	@Nonnull
    @Sequence(4)
	public U32 vregionflags=new U32();
	@Nonnull
    @Sequence(5)
	public U8 vwaterheight=new U8();
	@Nonnull
    @Sequence(6)
	public U8 vagents=new U8();
	@Nonnull
    @Sequence(7)
	public LLUUID vmapimageid=new LLUUID();
}
