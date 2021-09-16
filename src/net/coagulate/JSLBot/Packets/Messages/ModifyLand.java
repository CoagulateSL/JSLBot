package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ModifyLand extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 124; }
	@Nonnull
    public final String getName() { return "ModifyLand"; }
	@Nonnull
    @Sequence(0)
	public ModifyLand_bAgentData bagentdata=new ModifyLand_bAgentData();
	@Nonnull
    @Sequence(1)
	public ModifyLand_bModifyBlock bmodifyblock=new ModifyLand_bModifyBlock();
	@Sequence(2)
	public List<ModifyLand_bParcelData> bparceldata;
	@Sequence(3)
	public List<ModifyLand_bModifyBlockExtended> bmodifyblockextended;
	public ModifyLand(){}
	public ModifyLand(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
