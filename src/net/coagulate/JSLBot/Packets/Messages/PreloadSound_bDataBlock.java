package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class PreloadSound_bDataBlock extends Block {
	@Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Sequence(1)
	public LLUUID vownerid=new LLUUID();
	@Sequence(2)
	public LLUUID vsoundid=new LLUUID();
}
