package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ForceScriptControlRelease extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 192; }
	@Nonnull
    public final String getName() { return "ForceScriptControlRelease"; }
	@Nonnull
    @Sequence(0)
	public ForceScriptControlRelease_bAgentData bagentdata=new ForceScriptControlRelease_bAgentData();
	public ForceScriptControlRelease(){}
	public ForceScriptControlRelease(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
