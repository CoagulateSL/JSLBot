package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class RequestXfer_bXferID extends Block {
	@Nonnull
    @Sequence(0)
	public U64 vid=new U64();
	@Nonnull
    @Sequence(1)
	public Variable1 vfilename=new Variable1();
	@Nonnull
    @Sequence(2)
	public U8 vfilepath=new U8();
	@Nonnull
    @Sequence(3)
	public BOOL vdeleteoncompletion=new BOOL();
	@Nonnull
    @Sequence(4)
	public BOOL vusebigpackets=new BOOL();
	@Nonnull
    @Sequence(5)
	public LLUUID vvfileid=new LLUUID();
	@Nonnull
    @Sequence(6)
	public S16 vvfiletype=new S16();
}
