package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.U8;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class ChatFromViewer_bChatData extends Block {
	@Nonnull
    @Sequence(0)
	public Variable2 vmessage=new Variable2();
	@Nonnull
    @Sequence(1)
	public U8 vtype=new U8();
	@Nonnull
    @Sequence(2)
	public S32 vchannel=new S32();
}
