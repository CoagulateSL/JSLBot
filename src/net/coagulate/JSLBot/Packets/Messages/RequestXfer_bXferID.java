package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestXfer_bXferID extends Block {
	@Sequence(0)
	public U64 vid=new U64();
	@Sequence(1)
	public Variable1 vfilename=new Variable1();
	@Sequence(2)
	public U8 vfilepath=new U8();
	@Sequence(3)
	public BOOL vdeleteoncompletion=new BOOL();
	@Sequence(4)
	public BOOL vusebigpackets=new BOOL();
	@Sequence(5)
	public LLUUID vvfileid=new LLUUID();
	@Sequence(6)
	public S16 vvfiletype=new S16();
}
