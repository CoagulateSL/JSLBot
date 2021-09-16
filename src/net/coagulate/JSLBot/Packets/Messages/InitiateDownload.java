package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class InitiateDownload extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 403; }
	@Nonnull
    public final String getName() { return "InitiateDownload"; }
	@Nonnull
    @Sequence(0)
	public InitiateDownload_bAgentData bagentdata=new InitiateDownload_bAgentData();
	@Nonnull
    @Sequence(1)
	public InitiateDownload_bFileData bfiledata=new InitiateDownload_bFileData();
	public InitiateDownload(){}
	public InitiateDownload(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
