package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U64;
import net.coagulate.JSLBot.Packets.Types.U8;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class GroupRoleUpdate_bRoleData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vroleid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(2)
	public Variable1 vdescription=new Variable1();
	@Nonnull
    @Sequence(3)
	public Variable1 vtitle=new Variable1();
	@Nonnull
    @Sequence(4)
	public U64 vpowers=new U64();
	@Nonnull
    @Sequence(5)
	public U8 vupdatetype=new U8();
}
