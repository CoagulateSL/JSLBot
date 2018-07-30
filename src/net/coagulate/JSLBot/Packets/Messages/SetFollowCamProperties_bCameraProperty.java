package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SetFollowCamProperties_bCameraProperty extends Block {
	@Sequence(0)
	public S32 vtype=new S32();
	@Sequence(1)
	public F32 vvalue=new F32();
}
