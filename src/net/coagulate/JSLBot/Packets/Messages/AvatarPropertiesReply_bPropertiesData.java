package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class AvatarPropertiesReply_bPropertiesData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vimageid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vflimageid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vpartnerid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public Variable2 vabouttext=new Variable2();
	@Nonnull
    @Sequence(4)
	public Variable1 vflabouttext=new Variable1();
	@Nonnull
    @Sequence(5)
	public Variable1 vbornon=new Variable1();
	@Nonnull
    @Sequence(6)
	public Variable1 vprofileurl=new Variable1();
	@Nonnull
    @Sequence(7)
	public Variable1 vchartermember=new Variable1();
	@Nonnull
    @Sequence(8)
	public U32 vflags=new U32();
}
