package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CreateTrustedCircuit_bDataBlock extends Block {
	@Sequence(0)
	public LLUUID vendpointid=new LLUUID();
	@Sequence(1)
	public Fixed32 vdigest=new Fixed32();
}
