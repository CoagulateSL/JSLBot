package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarSitResponse_bSitTransform extends Block {
	@Sequence(0)
	public BOOL vautopilot=new BOOL();
	@Sequence(1)
	public LLVector3 vsitposition=new LLVector3();
	@Sequence(2)
	public LLQuaternion vsitrotation=new LLQuaternion();
	@Sequence(3)
	public LLVector3 vcameraeyeoffset=new LLVector3();
	@Sequence(4)
	public LLVector3 vcameraatoffset=new LLVector3();
	@Sequence(5)
	public BOOL vforcemouselook=new BOOL();
}
