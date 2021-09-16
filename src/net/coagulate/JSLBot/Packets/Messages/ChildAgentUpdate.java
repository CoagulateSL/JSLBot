package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ChildAgentUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 25; }
	@Nonnull
    public final String getName() { return "ChildAgentUpdate"; }
	@Nonnull
    @Sequence(0)
	public ChildAgentUpdate_bAgentData bagentdata=new ChildAgentUpdate_bAgentData();
	@Sequence(1)
	public List<ChildAgentUpdate_bGroupData> bgroupdata;
	@Sequence(2)
	public List<ChildAgentUpdate_bAnimationData> banimationdata;
	@Sequence(3)
	public List<ChildAgentUpdate_bGranterBlock> bgranterblock;
	@Sequence(4)
	public List<ChildAgentUpdate_bNVPairData> bnvpairdata;
	@Sequence(5)
	public List<ChildAgentUpdate_bVisualParam> bvisualparam;
	@Sequence(6)
	public List<ChildAgentUpdate_bAgentAccess> bagentaccess;
	@Sequence(7)
	public List<ChildAgentUpdate_bAgentInfo> bagentinfo;
	@Sequence(8)
	public List<ChildAgentUpdate_bAgentInventoryHost> bagentinventoryhost;
	public ChildAgentUpdate(){}
	public ChildAgentUpdate(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
