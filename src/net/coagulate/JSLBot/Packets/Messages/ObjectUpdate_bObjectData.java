package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectUpdate_bObjectData extends Block {
	@Sequence(0)
	public U32 vid=new U32();
	@Sequence(1)
	public U8 vstate=new U8();
	@Sequence(2)
	public LLUUID vfullid=new LLUUID();
	@Sequence(3)
	public U32 vcrc=new U32();
	@Sequence(4)
	public U8 vpcode=new U8();
	@Sequence(5)
	public U8 vmaterial=new U8();
	@Sequence(6)
	public U8 vclickaction=new U8();
	@Sequence(7)
	public LLVector3 vscale=new LLVector3();
	@Sequence(8)
	public Variable1 vobjectdata=new Variable1();
	@Sequence(9)
	public U32 vparentid=new U32();
	@Sequence(10)
	public U32 vupdateflags=new U32();
	@Sequence(11)
	public U8 vpathcurve=new U8();
	@Sequence(12)
	public U8 vprofilecurve=new U8();
	@Sequence(13)
	public U16 vpathbegin=new U16();
	@Sequence(14)
	public U16 vpathend=new U16();
	@Sequence(15)
	public U8 vpathscalex=new U8();
	@Sequence(16)
	public U8 vpathscaley=new U8();
	@Sequence(17)
	public U8 vpathshearx=new U8();
	@Sequence(18)
	public U8 vpathsheary=new U8();
	@Sequence(19)
	public S8 vpathtwist=new S8();
	@Sequence(20)
	public S8 vpathtwistbegin=new S8();
	@Sequence(21)
	public S8 vpathradiusoffset=new S8();
	@Sequence(22)
	public S8 vpathtaperx=new S8();
	@Sequence(23)
	public S8 vpathtapery=new S8();
	@Sequence(24)
	public U8 vpathrevolutions=new U8();
	@Sequence(25)
	public S8 vpathskew=new S8();
	@Sequence(26)
	public U16 vprofilebegin=new U16();
	@Sequence(27)
	public U16 vprofileend=new U16();
	@Sequence(28)
	public U16 vprofilehollow=new U16();
	@Sequence(29)
	public Variable2 vtextureentry=new Variable2();
	@Sequence(30)
	public Variable1 vtextureanim=new Variable1();
	@Sequence(31)
	public Variable2 vnamevalue=new Variable2();
	@Sequence(32)
	public Variable2 vdata=new Variable2();
	@Sequence(33)
	public Variable1 vtext=new Variable1();
	@Sequence(34)
	public Fixed4 vtextcolor=new Fixed4();
	@Sequence(35)
	public Variable1 vmediaurl=new Variable1();
	@Sequence(36)
	public Variable1 vpsblock=new Variable1();
	@Sequence(37)
	public Variable1 vextraparams=new Variable1();
	@Sequence(38)
	public LLUUID vsound=new LLUUID();
	@Sequence(39)
	public LLUUID vownerid=new LLUUID();
	@Sequence(40)
	public F32 vgain=new F32();
	@Sequence(41)
	public U8 vflags=new U8();
	@Sequence(42)
	public F32 vradius=new F32();
	@Sequence(43)
	public U8 vjointtype=new U8();
	@Sequence(44)
	public LLVector3 vjointpivot=new LLVector3();
	@Sequence(45)
	public LLVector3 vjointaxisoranchor=new LLVector3();
}
