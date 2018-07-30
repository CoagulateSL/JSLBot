package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AttachedSound_bDataBlock extends Block {
	@Sequence(0)
	public LLUUID vsoundid=new LLUUID();
	@Sequence(1)
	public LLUUID vobjectid=new LLUUID();
	@Sequence(2)
	public LLUUID vownerid=new LLUUID();
	@Sequence(3)
	public F32 vgain=new F32();
	@Sequence(4)
	public U8 vflags=new U8();
}
