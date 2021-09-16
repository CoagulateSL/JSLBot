package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U8;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class AvatarTextureUpdate_bWearableData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vcacheid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U8 vtextureindex=new U8();
	@Nonnull
    @Sequence(2)
	public Variable1 vhostname=new Variable1();
}
