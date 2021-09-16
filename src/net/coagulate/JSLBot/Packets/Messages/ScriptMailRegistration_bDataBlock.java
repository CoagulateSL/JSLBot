package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.IPPORT;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class ScriptMailRegistration_bDataBlock extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vtargetip=new Variable1();
	@Nonnull
    @Sequence(1)
	public IPPORT vtargetport=new IPPORT();
	@Nonnull
    @Sequence(2)
	public LLUUID vtaskid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public U32 vflags=new U32();
}
