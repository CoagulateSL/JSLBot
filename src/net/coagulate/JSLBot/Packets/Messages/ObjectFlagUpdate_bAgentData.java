package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class ObjectFlagUpdate_bAgentData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public LLUUID vsessionid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public U32 vobjectlocalid=new U32();
	@Nonnull
    @Sequence(3)
	public BOOL vusephysics=new BOOL();
	@Nonnull
    @Sequence(4)
	public BOOL vistemporary=new BOOL();
	@Nonnull
    @Sequence(5)
	public BOOL visphantom=new BOOL();
	@Nonnull
    @Sequence(6)
	public BOOL vcastsshadows=new BOOL();
}
