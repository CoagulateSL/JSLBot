package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U64;

import javax.annotation.Nonnull;

public class SoundTrigger_bSoundData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vsoundid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vobjectid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public LLUUID vparentid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public U64 vhandle=new U64();
	@Nonnull
    @Sequence(5)
	public LLVector3 vposition=new LLVector3();
	@Nonnull
    @Sequence(6)
	public F32 vgain=new F32();
}
