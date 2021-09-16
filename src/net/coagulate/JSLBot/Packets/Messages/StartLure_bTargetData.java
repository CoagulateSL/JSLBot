package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

import javax.annotation.Nullable;

public class StartLure_bTargetData extends Block {
	@Nullable
    @Sequence(0)
	public LLUUID vtargetid=new LLUUID();
}
