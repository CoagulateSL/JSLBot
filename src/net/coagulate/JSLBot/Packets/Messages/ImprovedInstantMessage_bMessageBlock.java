package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ImprovedInstantMessage_bMessageBlock extends Block {
	@Sequence(0)
	public BOOL vfromgroup=new BOOL();
	@Sequence(1)
	public LLUUID vtoagentid=new LLUUID();
	@Sequence(2)
	public U32 vparentestateid=new U32();
	@Sequence(3)
	public LLUUID vregionid=new LLUUID();
	@Sequence(4)
	public LLVector3 vposition=new LLVector3();
	@Sequence(5)
	public U8 voffline=new U8();
	@Sequence(6)
	public U8 vdialog=new U8();
	@Sequence(7)
	public LLUUID vid=new LLUUID();
	@Sequence(8)
	public U32 vtimestamp=new U32();
	@Sequence(9)
	public Variable1 vfromagentname=new Variable1();
	@Sequence(10)
	public Variable2 vmessage=new Variable2();
	@Sequence(11)
	public Variable2 vbinarybucket=new Variable2();
}
