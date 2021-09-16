package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class RemoveNameValuePair extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 330; }
	@Nonnull
    public final String getName() { return "RemoveNameValuePair"; }
	@Nonnull
    @Sequence(0)
	public RemoveNameValuePair_bTaskData btaskdata=new RemoveNameValuePair_bTaskData();
	@Sequence(1)
	public List<RemoveNameValuePair_bNameValueData> bnamevaluedata;
}
