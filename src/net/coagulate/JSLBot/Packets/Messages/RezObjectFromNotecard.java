package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RezObjectFromNotecard extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 294; }
	public final String getName() { return "RezObjectFromNotecard"; }
	@Sequence(0)
	public RezObjectFromNotecard_bAgentData bagentdata=new RezObjectFromNotecard_bAgentData();
	@Sequence(1)
	public RezObjectFromNotecard_bRezData brezdata=new RezObjectFromNotecard_bRezData();
	@Sequence(2)
	public RezObjectFromNotecard_bNotecardData bnotecarddata=new RezObjectFromNotecard_bNotecardData();
	@Sequence(3)
	public List<RezObjectFromNotecard_bInventoryData> binventorydata;
}
