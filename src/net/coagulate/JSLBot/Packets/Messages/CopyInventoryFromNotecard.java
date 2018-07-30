package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CopyInventoryFromNotecard extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 265; }
	public final String getName() { return "CopyInventoryFromNotecard"; }
	@Sequence(0)
	public CopyInventoryFromNotecard_bAgentData bagentdata=new CopyInventoryFromNotecard_bAgentData();
	@Sequence(1)
	public CopyInventoryFromNotecard_bNotecardData bnotecarddata=new CopyInventoryFromNotecard_bNotecardData();
	@Sequence(2)
	public List<CopyInventoryFromNotecard_bInventoryData> binventorydata;
}
