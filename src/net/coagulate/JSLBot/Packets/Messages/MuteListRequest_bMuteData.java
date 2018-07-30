package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MuteListRequest_bMuteData extends Block {
	@Sequence(0)
	public U32 vmutecrc=new U32();
}
