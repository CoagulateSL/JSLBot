package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MergeParcel extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 223; }
	public final String getName() { return "MergeParcel"; }
	@Sequence(0)
	public MergeParcel_bMasterParcelData bmasterparceldata=new MergeParcel_bMasterParcelData();
	@Sequence(1)
	public List<MergeParcel_bSlaveParcelData> bslaveparceldata;
}
