package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U64;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class AgentDataUpdate_bAgentData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public Variable1 vfirstname=new Variable1();
	@Nonnull
    @Sequence(2)
	public Variable1 vlastname=new Variable1();
	@Nonnull
    @Sequence(3)
	public Variable1 vgrouptitle=new Variable1();
	@Nonnull
    @Sequence(4)
	public LLUUID vactivegroupid=new LLUUID();
	@Nonnull
    @Sequence(5)
	public U64 vgrouppowers=new U64();
	@Nonnull
    @Sequence(6)
	public Variable1 vgroupname=new Variable1();
}
