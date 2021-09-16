package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class ImprovedInstantMessage_bMessageBlock extends Block {
	@Nonnull
    @Sequence(0)
	public BOOL vfromgroup=new BOOL();
	@Sequence(1)
	public LLUUID vtoagentid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public U32 vparentestateid=new U32();
	@Nonnull
    @Sequence(3)
	public LLUUID vregionid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public LLVector3 vposition=new LLVector3();
	@Nonnull
    @Sequence(5)
	public U8 voffline=new U8();
	@Nonnull
    @Sequence(6)
	public U8 vdialog=new U8();
	@Sequence(7)
	public LLUUID vid=new LLUUID();
	@Nonnull
    @Sequence(8)
	public U32 vtimestamp=new U32();
	@Nonnull
    @Sequence(9)
	public Variable1 vfromagentname=new Variable1();
	@Nonnull
    @Sequence(10)
	public Variable2 vmessage=new Variable2();
	@Nonnull
    @Sequence(11)
	public Variable2 vbinarybucket=new Variable2();
}
