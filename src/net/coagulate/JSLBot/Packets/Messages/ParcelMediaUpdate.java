package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelMediaUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 420; }
	public final String getName() { return "ParcelMediaUpdate"; }
	@Sequence(0)
	public ParcelMediaUpdate_bDataBlock bdatablock=new ParcelMediaUpdate_bDataBlock();
	@Sequence(1)
	public ParcelMediaUpdate_bDataBlockExtended bdatablockextended=new ParcelMediaUpdate_bDataBlockExtended();
}
