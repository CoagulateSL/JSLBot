package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AttachedSoundGainChange_bDataBlock extends Block {
	@Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Sequence(1)
	public F32 vgain=new F32();
}
