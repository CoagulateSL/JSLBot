package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class Redo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 76; }
	@Nonnull
    public final String getName() { return "Redo"; }
	@Nonnull
    @Sequence(0)
	public Redo_bAgentData bagentdata=new Redo_bAgentData();
	@Sequence(1)
	public List<Redo_bObjectData> bobjectdata;
	public Redo(){}
	public Redo(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
