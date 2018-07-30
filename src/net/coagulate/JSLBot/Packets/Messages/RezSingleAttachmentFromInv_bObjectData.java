package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RezSingleAttachmentFromInv_bObjectData extends Block {
	@Sequence(0)
	public LLUUID vitemid=new LLUUID();
	@Sequence(1)
	public LLUUID vownerid=new LLUUID();
	@Sequence(2)
	public U8 vattachmentpt=new U8();
	@Sequence(3)
	public U32 vitemflags=new U32();
	@Sequence(4)
	public U32 vgroupmask=new U32();
	@Sequence(5)
	public U32 veveryonemask=new U32();
	@Sequence(6)
	public U32 vnextownermask=new U32();
	@Sequence(7)
	public Variable1 vname=new Variable1();
	@Sequence(8)
	public Variable1 vdescription=new Variable1();
}
