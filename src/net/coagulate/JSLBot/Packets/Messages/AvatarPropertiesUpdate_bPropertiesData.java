package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.Variable1;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class AvatarPropertiesUpdate_bPropertiesData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vimageid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vflimageid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public Variable2 vabouttext=new Variable2();
	@Nonnull
    @Sequence(3)
	public Variable1 vflabouttext=new Variable1();
	@Nonnull
    @Sequence(4)
	public BOOL vallowpublish=new BOOL();
	@Nonnull
    @Sequence(5)
	public BOOL vmaturepublish=new BOOL();
	@Nonnull
    @Sequence(6)
	public Variable1 vprofileurl=new Variable1();
}
