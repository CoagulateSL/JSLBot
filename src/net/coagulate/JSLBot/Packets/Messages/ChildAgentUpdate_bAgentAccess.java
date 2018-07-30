package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ChildAgentUpdate_bAgentAccess extends Block {
	@Sequence(0)
	public U8 vagentlegacyaccess=new U8();
	@Sequence(1)
	public U8 vagentmaxaccess=new U8();
}
