package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestObjectPropertiesFamily_bObjectData extends Block {
	@Sequence(0)
	public U32 vrequestflags=new U32();
	@Sequence(1)
	public LLUUID vobjectid=new LLUUID();
}
