package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nullable;

public class MapNameRequest_bNameData extends Block {
	@Nullable
    @Sequence(0)
	public Variable1 vname=new Variable1();
}
