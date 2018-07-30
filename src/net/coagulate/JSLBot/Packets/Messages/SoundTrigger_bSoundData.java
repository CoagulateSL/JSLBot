package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SoundTrigger_bSoundData extends Block {
	@Sequence(0)
	public LLUUID vsoundid=new LLUUID();
	@Sequence(1)
	public LLUUID vownerid=new LLUUID();
	@Sequence(2)
	public LLUUID vobjectid=new LLUUID();
	@Sequence(3)
	public LLUUID vparentid=new LLUUID();
	@Sequence(4)
	public U64 vhandle=new U64();
	@Sequence(5)
	public LLVector3 vposition=new LLVector3();
	@Sequence(6)
	public F32 vgain=new F32();
}
