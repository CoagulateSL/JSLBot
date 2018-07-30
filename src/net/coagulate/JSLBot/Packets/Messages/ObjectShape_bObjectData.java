package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectShape_bObjectData extends Block {
	@Sequence(0)
	public U32 vobjectlocalid=new U32();
	@Sequence(1)
	public U8 vpathcurve=new U8();
	@Sequence(2)
	public U8 vprofilecurve=new U8();
	@Sequence(3)
	public U16 vpathbegin=new U16();
	@Sequence(4)
	public U16 vpathend=new U16();
	@Sequence(5)
	public U8 vpathscalex=new U8();
	@Sequence(6)
	public U8 vpathscaley=new U8();
	@Sequence(7)
	public U8 vpathshearx=new U8();
	@Sequence(8)
	public U8 vpathsheary=new U8();
	@Sequence(9)
	public S8 vpathtwist=new S8();
	@Sequence(10)
	public S8 vpathtwistbegin=new S8();
	@Sequence(11)
	public S8 vpathradiusoffset=new S8();
	@Sequence(12)
	public S8 vpathtaperx=new S8();
	@Sequence(13)
	public S8 vpathtapery=new S8();
	@Sequence(14)
	public U8 vpathrevolutions=new U8();
	@Sequence(15)
	public S8 vpathskew=new S8();
	@Sequence(16)
	public U16 vprofilebegin=new U16();
	@Sequence(17)
	public U16 vprofileend=new U16();
	@Sequence(18)
	public U16 vprofilehollow=new U16();
}
