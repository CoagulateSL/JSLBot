package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U64;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class GroupDataUpdate_bAgentGroupData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vgroupid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public U64 vagentpowers=new U64();
	@Nonnull
    @Sequence(3)
	public Variable1 vgrouptitle=new Variable1();
}
