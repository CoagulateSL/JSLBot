package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class CopyInventoryFromNotecard extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 265; }
	@Nonnull
    public final String getName() { return "CopyInventoryFromNotecard"; }
	@Nonnull
    @Sequence(0)
	public CopyInventoryFromNotecard_bAgentData bagentdata=new CopyInventoryFromNotecard_bAgentData();
	@Nonnull
    @Sequence(1)
	public CopyInventoryFromNotecard_bNotecardData bnotecarddata=new CopyInventoryFromNotecard_bNotecardData();
	@Sequence(2)
	public List<CopyInventoryFromNotecard_bInventoryData> binventorydata;
	public CopyInventoryFromNotecard(){}
	public CopyInventoryFromNotecard(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
