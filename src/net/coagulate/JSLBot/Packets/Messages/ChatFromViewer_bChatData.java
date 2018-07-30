package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ChatFromViewer_bChatData extends Block {
	@Sequence(0)
	public Variable2 vmessage=new Variable2();
	@Sequence(1)
	public U8 vtype=new U8();
	@Sequence(2)
	public S32 vchannel=new S32();
}
