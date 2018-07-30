package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestImage_bRequestImage extends Block {
	@Sequence(0)
	public LLUUID vimage=new LLUUID();
	@Sequence(1)
	public S8 vdiscardlevel=new S8();
	@Sequence(2)
	public F32 vdownloadpriority=new F32();
	@Sequence(3)
	public U32 vpacket=new U32();
	@Sequence(4)
	public U8 vtype=new U8();
}
