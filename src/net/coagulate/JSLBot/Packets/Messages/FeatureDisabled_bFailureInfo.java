package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class FeatureDisabled_bFailureInfo extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 verrormessage=new Variable1();
	@Nonnull
    @Sequence(1)
	public LLUUID vagentid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vtransactionid=new LLUUID();
}
