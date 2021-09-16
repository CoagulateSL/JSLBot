package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class StartLure extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 70; }
	@Nonnull
    public final String getName() { return "StartLure"; }
	@Nonnull
    @Sequence(0)
	public StartLure_bAgentData bagentdata=new StartLure_bAgentData();
	@Nonnull
    @Sequence(1)
	public StartLure_bInfo binfo=new StartLure_bInfo();
	@Sequence(2)
	public List<StartLure_bTargetData> btargetdata;
	public StartLure(){}
	public StartLure(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
