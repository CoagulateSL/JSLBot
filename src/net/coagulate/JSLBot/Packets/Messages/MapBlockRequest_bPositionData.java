package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MapBlockRequest_bPositionData extends Block {
	@Sequence(0)
	public U16 vminx=new U16();
	@Sequence(1)
	public U16 vmaxx=new U16();
	@Sequence(2)
	public U16 vminy=new U16();
	@Sequence(3)
	public U16 vmaxy=new U16();
}
