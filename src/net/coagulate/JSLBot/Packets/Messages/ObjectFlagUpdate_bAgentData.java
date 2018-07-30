package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectFlagUpdate_bAgentData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public LLUUID vsessionid=new LLUUID();
	@Sequence(2)
	public U32 vobjectlocalid=new U32();
	@Sequence(3)
	public BOOL vusephysics=new BOOL();
	@Sequence(4)
	public BOOL vistemporary=new BOOL();
	@Sequence(5)
	public BOOL visphantom=new BOOL();
	@Sequence(6)
	public BOOL vcastsshadows=new BOOL();
}
