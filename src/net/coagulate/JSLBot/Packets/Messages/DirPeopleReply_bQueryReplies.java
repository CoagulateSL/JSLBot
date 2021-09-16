package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class DirPeopleReply_bQueryReplies extends Block {
	@Nonnull
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
	public Variable1 vgroup=new Variable1();
	@Nonnull
    @Sequence(4)
	public BOOL vonline=new BOOL();
	@Nonnull
    @Sequence(5)
	public S32 vreputation=new S32();
}
