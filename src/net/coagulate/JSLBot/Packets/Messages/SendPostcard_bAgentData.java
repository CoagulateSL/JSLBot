package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class SendPostcard_bAgentData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public LLUUID vsessionid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vassetid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public LLVector3d vposglobal=new LLVector3d();
	@Nonnull
    @Sequence(4)
	public Variable1 vto=new Variable1();
	@Nonnull
    @Sequence(5)
	public Variable1 vfrom=new Variable1();
	@Nonnull
    @Sequence(6)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(7)
	public Variable1 vsubject=new Variable1();
	@Nonnull
    @Sequence(8)
	public Variable2 vmsg=new Variable2();
	@Nonnull
    @Sequence(9)
	public BOOL vallowpublish=new BOOL();
	@Nonnull
    @Sequence(10)
	public BOOL vmaturepublish=new BOOL();
}
