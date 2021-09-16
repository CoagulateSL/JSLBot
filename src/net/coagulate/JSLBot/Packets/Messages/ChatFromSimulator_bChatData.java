package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class ChatFromSimulator_bChatData extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vfromname=new Variable1();
	@Nonnull
    @Sequence(1)
	public LLUUID vsourceid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public U8 vsourcetype=new U8();
	@Nonnull
    @Sequence(4)
	public U8 vchattype=new U8();
	@Nonnull
    @Sequence(5)
	public U8 vaudible=new U8();
	@Nonnull
    @Sequence(6)
	public LLVector3 vposition=new LLVector3();
	@Nonnull
    @Sequence(7)
	public Variable2 vmessage=new Variable2();
}
