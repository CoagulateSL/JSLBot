package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupNoticeAdd_bMessageBlock extends Block {
	@Sequence(0)
	public LLUUID vtogroupid=new LLUUID();
	@Sequence(1)
	public LLUUID vid=new LLUUID();
	@Sequence(2)
	public U8 vdialog=new U8();
	@Sequence(3)
	public Variable1 vfromagentname=new Variable1();
	@Sequence(4)
	public Variable2 vmessage=new Variable2();
	@Sequence(5)
	public Variable2 vbinarybucket=new Variable2();
}
