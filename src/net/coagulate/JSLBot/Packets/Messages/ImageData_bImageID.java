package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ImageData_bImageID extends Block {
	@Sequence(0)
	public LLUUID vid=new LLUUID();
	@Sequence(1)
	public U8 vcodec=new U8();
	@Sequence(2)
	public U32 vsize=new U32();
	@Sequence(3)
	public U16 vpackets=new U16();
}
