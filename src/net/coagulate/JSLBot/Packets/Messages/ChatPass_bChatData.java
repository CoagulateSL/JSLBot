package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class ChatPass_bChatData extends Block {
	@Nonnull
    @Sequence(0)
	public S32 vchannel=new S32();
	@Nonnull
    @Sequence(1)
	public LLVector3 vposition=new LLVector3();
	@Nonnull
    @Sequence(2)
	public LLUUID vid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(5)
	public U8 vsourcetype=new U8();
	@Nonnull
    @Sequence(6)
	public U8 vtype=new U8();
	@Nonnull
    @Sequence(7)
	public F32 vradius=new F32();
	@Nonnull
    @Sequence(8)
	public U8 vsimaccess=new U8();
	@Nonnull
    @Sequence(9)
	public Variable2 vmessage=new Variable2();
}
