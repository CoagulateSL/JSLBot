package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class HealthMessage_bHealthData extends Block {
	@Sequence(0)
	public F32 vhealth=new F32();
}
