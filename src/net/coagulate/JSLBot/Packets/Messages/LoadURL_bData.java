package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class LoadURL_bData extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vobjectname=new Variable1();
	@Nonnull
    @Sequence(1)
	public LLUUID vobjectid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public BOOL vownerisgroup=new BOOL();
	@Nonnull
    @Sequence(4)
	public Variable1 vmessage=new Variable1();
	@Nonnull
    @Sequence(5)
	public Variable1 vurl=new Variable1();
}
