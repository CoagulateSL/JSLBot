package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectGrab_bSurfaceInfo extends Block {
	@Sequence(0)
	public LLVector3 vuvcoord=new LLVector3();
	@Sequence(1)
	public LLVector3 vstcoord=new LLVector3();
	@Sequence(2)
	public S32 vfaceindex=new S32();
	@Sequence(3)
	public LLVector3 vposition=new LLVector3();
	@Sequence(4)
	public LLVector3 vnormal=new LLVector3();
	@Sequence(5)
	public LLVector3 vbinormal=new LLVector3();
}
