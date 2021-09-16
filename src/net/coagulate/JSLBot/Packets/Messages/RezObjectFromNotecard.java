package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class RezObjectFromNotecard extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 294; }
	@Nonnull
    public final String getName() { return "RezObjectFromNotecard"; }
	@Nonnull
    @Sequence(0)
	public RezObjectFromNotecard_bAgentData bagentdata=new RezObjectFromNotecard_bAgentData();
	@Nonnull
    @Sequence(1)
	public RezObjectFromNotecard_bRezData brezdata=new RezObjectFromNotecard_bRezData();
	@Nonnull
    @Sequence(2)
	public RezObjectFromNotecard_bNotecardData bnotecarddata=new RezObjectFromNotecard_bNotecardData();
	@Sequence(3)
	public List<RezObjectFromNotecard_bInventoryData> binventorydata;
	public RezObjectFromNotecard(){}
	public RezObjectFromNotecard(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
