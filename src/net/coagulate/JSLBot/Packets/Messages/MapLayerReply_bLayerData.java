package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MapLayerReply_bLayerData extends Block {
	@Sequence(0)
	public U32 vleft=new U32();
	@Sequence(1)
	public U32 vright=new U32();
	@Sequence(2)
	public U32 vtop=new U32();
	@Sequence(3)
	public U32 vbottom=new U32();
	@Sequence(4)
	public LLUUID vimageid=new LLUUID();
}
