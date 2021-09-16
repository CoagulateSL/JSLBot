package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class ScriptDialogReply_bData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public S32 vchatchannel=new S32();
	@Nonnull
    @Sequence(2)
	public S32 vbuttonindex=new S32();
	@Nonnull
    @Sequence(3)
	public Variable1 vbuttonlabel=new Variable1();
}
