package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.Variable1;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class ScriptDialog_bData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public Variable1 vfirstname=new Variable1();
	@Nonnull
    @Sequence(2)
	public Variable1 vlastname=new Variable1();
	@Nonnull
    @Sequence(3)
	public Variable1 vobjectname=new Variable1();
	@Nonnull
    @Sequence(4)
	public Variable2 vmessage=new Variable2();
	@Nonnull
    @Sequence(5)
	public S32 vchatchannel=new S32();
	@Nonnull
    @Sequence(6)
	public LLUUID vimageid=new LLUUID();
}
