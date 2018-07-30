package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class FeatureDisabled_bFailureInfo extends Block {
	@Sequence(0)
	public Variable1 verrormessage=new Variable1();
	@Sequence(1)
	public LLUUID vagentid=new LLUUID();
	@Sequence(2)
	public LLUUID vtransactionid=new LLUUID();
}
