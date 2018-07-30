package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectAdd_bObjectData extends Block {
	@Sequence(0)
	public U8 vpcode=new U8();
	@Sequence(1)
	public U8 vmaterial=new U8();
	@Sequence(2)
	public U32 vaddflags=new U32();
	@Sequence(3)
	public U8 vpathcurve=new U8();
	@Sequence(4)
	public U8 vprofilecurve=new U8();
	@Sequence(5)
	public U16 vpathbegin=new U16();
	@Sequence(6)
	public U16 vpathend=new U16();
	@Sequence(7)
	public U8 vpathscalex=new U8();
	@Sequence(8)
	public U8 vpathscaley=new U8();
	@Sequence(9)
	public U8 vpathshearx=new U8();
	@Sequence(10)
	public U8 vpathsheary=new U8();
	@Sequence(11)
	public S8 vpathtwist=new S8();
	@Sequence(12)
	public S8 vpathtwistbegin=new S8();
	@Sequence(13)
	public S8 vpathradiusoffset=new S8();
	@Sequence(14)
	public S8 vpathtaperx=new S8();
	@Sequence(15)
	public S8 vpathtapery=new S8();
	@Sequence(16)
	public U8 vpathrevolutions=new U8();
	@Sequence(17)
	public S8 vpathskew=new S8();
	@Sequence(18)
	public U16 vprofilebegin=new U16();
	@Sequence(19)
	public U16 vprofileend=new U16();
	@Sequence(20)
	public U16 vprofilehollow=new U16();
	@Sequence(21)
	public U8 vbypassraycast=new U8();
	@Sequence(22)
	public LLVector3 vraystart=new LLVector3();
	@Sequence(23)
	public LLVector3 vrayend=new LLVector3();
	@Sequence(24)
	public LLUUID vraytargetid=new LLUUID();
	@Sequence(25)
	public U8 vrayendisintersection=new U8();
	@Sequence(26)
	public LLVector3 vscale=new LLVector3();
	@Sequence(27)
	public LLQuaternion vrotation=new LLQuaternion();
	@Sequence(28)
	public U8 vstate=new U8();
}
