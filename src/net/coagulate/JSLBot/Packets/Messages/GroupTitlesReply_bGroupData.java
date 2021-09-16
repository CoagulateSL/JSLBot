package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class GroupTitlesReply_bGroupData extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vtitle=new Variable1();
	@Nonnull
    @Sequence(1)
	public LLUUID vroleid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public BOOL vselected=new BOOL();
}
