package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class ScriptSensorRequest_bRequester extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vsourceid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vrequestid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vsearchid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public LLVector3 vsearchpos=new LLVector3();
	@Nonnull
    @Sequence(4)
	public LLQuaternion vsearchdir=new LLQuaternion();
	@Nonnull
    @Sequence(5)
	public Variable1 vsearchname=new Variable1();
	@Nonnull
    @Sequence(6)
	public S32 vtype=new S32();
	@Nonnull
    @Sequence(7)
	public F32 vrange=new F32();
	@Nonnull
    @Sequence(8)
	public F32 varc=new F32();
	@Nonnull
    @Sequence(9)
	public U64 vregionhandle=new U64();
	@Nonnull
    @Sequence(10)
	public U8 vsearchregions=new U8();
}
