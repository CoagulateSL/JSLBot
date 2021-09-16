package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLQuaternion;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class TelehubInfo_bTelehubBlock extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public Variable1 vobjectname=new Variable1();
	@Nonnull
    @Sequence(2)
	public LLVector3 vtelehubpos=new LLVector3();
	@Nonnull
    @Sequence(3)
	public LLQuaternion vtelehubrot=new LLQuaternion();
}
