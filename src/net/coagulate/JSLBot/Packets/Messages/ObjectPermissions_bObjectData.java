package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectPermissions_bObjectData extends Block {
	@Sequence(0)
	public U32 vobjectlocalid=new U32();
	@Sequence(1)
	public U8 vfield=new U8();
	@Sequence(2)
	public U8 vset=new U8();
	@Sequence(3)
	public U32 vmask=new U32();
}
