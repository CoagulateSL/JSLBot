package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class GroupMembersReply_bMemberData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public S32 vcontribution=new S32();
	@Nonnull
    @Sequence(2)
	public Variable1 vonlinestatus=new Variable1();
	@Nonnull
    @Sequence(3)
	public U64 vagentpowers=new U64();
	@Nonnull
    @Sequence(4)
	public Variable1 vtitle=new Variable1();
	@Nonnull
    @Sequence(5)
	public BOOL visowner=new BOOL();
}
