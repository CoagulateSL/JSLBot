package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ScriptDialogReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 191; }
	@Nonnull
    public final String getName() { return "ScriptDialogReply"; }
	@Nonnull
    @Sequence(0)
	public ScriptDialogReply_bAgentData bagentdata=new ScriptDialogReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public ScriptDialogReply_bData bdata=new ScriptDialogReply_bData();
	public ScriptDialogReply(){}
	public ScriptDialogReply(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
