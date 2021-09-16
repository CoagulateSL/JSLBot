package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class ChildAgentPositionUpdate_bAgentData extends Block {
	@Nonnull
    @Sequence(0)
	public U64 vregionhandle=new U64();
	@Nonnull
    @Sequence(1)
	public U32 vviewercircuitcode=new U32();
	@Sequence(2)
	public LLUUID vagentid=new LLUUID();
	@Sequence(3)
	public LLUUID vsessionid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public LLVector3 vagentpos=new LLVector3();
	@Nonnull
    @Sequence(5)
	public LLVector3 vagentvel=new LLVector3();
	@Nonnull
    @Sequence(6)
	public LLVector3 vcenter=new LLVector3();
	@Nonnull
    @Sequence(7)
	public LLVector3 vsize=new LLVector3();
	@Nonnull
    @Sequence(8)
	public LLVector3 vataxis=new LLVector3();
	@Nonnull
    @Sequence(9)
	public LLVector3 vleftaxis=new LLVector3();
	@Nonnull
    @Sequence(10)
	public LLVector3 vupaxis=new LLVector3();
	@Nonnull
    @Sequence(11)
	public BOOL vchangedgrid=new BOOL();
}
