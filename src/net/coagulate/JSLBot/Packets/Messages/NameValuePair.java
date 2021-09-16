package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class NameValuePair extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 329; }
	@Nonnull
    public final String getName() { return "NameValuePair"; }
	@Nonnull
    @Sequence(0)
	public NameValuePair_bTaskData btaskdata=new NameValuePair_bTaskData();
	@Sequence(1)
	public List<NameValuePair_bNameValueData> bnamevaluedata;
}
