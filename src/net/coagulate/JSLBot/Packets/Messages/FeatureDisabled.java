package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class FeatureDisabled extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 19; }
	public final String getName() { return "FeatureDisabled"; }
	@Sequence(0)
	public FeatureDisabled_bFailureInfo bfailureinfo=new FeatureDisabled_bFailureInfo();
}
