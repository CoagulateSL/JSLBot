package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarTextureUpdate_bWearableData extends Block {
	@Sequence(0)
	public LLUUID vcacheid=new LLUUID();
	@Sequence(1)
	public U8 vtextureindex=new U8();
	@Sequence(2)
	public Variable1 vhostname=new Variable1();
}
