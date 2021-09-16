package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class ChildAgentUpdate_bAgentData extends Block {
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
	@Nonnull
    @Sequence(12)
	public F32 vfar=new F32();
	@Nonnull
    @Sequence(13)
	public F32 vaspect=new F32();
	@Nonnull
    @Sequence(14)
	public Variable1 vthrottles=new Variable1();
	@Nonnull
    @Sequence(15)
	public U32 vlocomotionstate=new U32();
	@Nonnull
    @Sequence(16)
	public LLQuaternion vheadrotation=new LLQuaternion();
	@Nonnull
    @Sequence(17)
	public LLQuaternion vbodyrotation=new LLQuaternion();
	@Nonnull
    @Sequence(18)
	public U32 vcontrolflags=new U32();
	@Nonnull
    @Sequence(19)
	public F32 venergylevel=new F32();
	@Nonnull
    @Sequence(20)
	public U8 vgodlevel=new U8();
	@Nonnull
    @Sequence(21)
	public BOOL valwaysrun=new BOOL();
	@Nonnull
    @Sequence(22)
	public LLUUID vpreyagent=new LLUUID();
	@Nonnull
    @Sequence(23)
	public U8 vagentaccess=new U8();
	@Nonnull
    @Sequence(24)
	public Variable2 vagenttextures=new Variable2();
	@Nonnull
    @Sequence(25)
	public LLUUID vactivegroupid=new LLUUID();
}
