package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U8;
import net.coagulate.JSLBot.Packets.Types.Variable1;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class GroupNoticeAdd_bMessageBlock extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vtogroupid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public U8 vdialog=new U8();
	@Nonnull
    @Sequence(3)
	public Variable1 vfromagentname=new Variable1();
	@Nonnull
    @Sequence(4)
	public Variable2 vmessage=new Variable2();
	@Nonnull
    @Sequence(5)
	public Variable2 vbinarybucket=new Variable2();
}
