package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F64;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class LogTextMessage_bDataBlock extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vfromagentid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vtoagentid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public F64 vglobalx=new F64();
	@Nonnull
    @Sequence(3)
	public F64 vglobaly=new F64();
	@Nonnull
    @Sequence(4)
	public U32 vtime=new U32();
	@Nonnull
    @Sequence(5)
	public Variable2 vmessage=new Variable2();
}
