package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectGrabUpdate_bObjectData extends Block {
	@Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Sequence(1)
	public LLVector3 vgraboffsetinitial=new LLVector3();
	@Sequence(2)
	public LLVector3 vgrabposition=new LLVector3();
	@Sequence(3)
	public U32 vtimesincelast=new U32();
}
