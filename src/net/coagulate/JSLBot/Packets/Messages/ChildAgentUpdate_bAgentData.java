package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ChildAgentUpdate_bAgentData extends Block {
	@Sequence(0)
	public U64 vregionhandle=new U64();
	@Sequence(1)
	public U32 vviewercircuitcode=new U32();
	@Sequence(2)
	public LLUUID vagentid=new LLUUID();
	@Sequence(3)
	public LLUUID vsessionid=new LLUUID();
	@Sequence(4)
	public LLVector3 vagentpos=new LLVector3();
	@Sequence(5)
	public LLVector3 vagentvel=new LLVector3();
	@Sequence(6)
	public LLVector3 vcenter=new LLVector3();
	@Sequence(7)
	public LLVector3 vsize=new LLVector3();
	@Sequence(8)
	public LLVector3 vataxis=new LLVector3();
	@Sequence(9)
	public LLVector3 vleftaxis=new LLVector3();
	@Sequence(10)
	public LLVector3 vupaxis=new LLVector3();
	@Sequence(11)
	public BOOL vchangedgrid=new BOOL();
	@Sequence(12)
	public F32 vfar=new F32();
	@Sequence(13)
	public F32 vaspect=new F32();
	@Sequence(14)
	public Variable1 vthrottles=new Variable1();
	@Sequence(15)
	public U32 vlocomotionstate=new U32();
	@Sequence(16)
	public LLQuaternion vheadrotation=new LLQuaternion();
	@Sequence(17)
	public LLQuaternion vbodyrotation=new LLQuaternion();
	@Sequence(18)
	public U32 vcontrolflags=new U32();
	@Sequence(19)
	public F32 venergylevel=new F32();
	@Sequence(20)
	public U8 vgodlevel=new U8();
	@Sequence(21)
	public BOOL valwaysrun=new BOOL();
	@Sequence(22)
	public LLUUID vpreyagent=new LLUUID();
	@Sequence(23)
	public U8 vagentaccess=new U8();
	@Sequence(24)
	public Variable2 vagenttextures=new Variable2();
	@Sequence(25)
	public LLUUID vactivegroupid=new LLUUID();
}
