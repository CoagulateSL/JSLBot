package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelProperties extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 23; }
	public final String getName() { return "ParcelProperties"; }
	@Sequence(0)
	public ParcelProperties_bParcelData bparceldata=new ParcelProperties_bParcelData();
	@Sequence(1)
	public ParcelProperties_bAgeVerificationBlock bageverificationblock=new ParcelProperties_bAgeVerificationBlock();
}
