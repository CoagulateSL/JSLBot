package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ModifyLand extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 124; }
	public final String getName() { return "ModifyLand"; }
	@Sequence(0)
	public ModifyLand_bAgentData bagentdata=new ModifyLand_bAgentData();
	@Sequence(1)
	public ModifyLand_bModifyBlock bmodifyblock=new ModifyLand_bModifyBlock();
	@Sequence(2)
	public List<ModifyLand_bParcelData> bparceldata;
	@Sequence(3)
	public List<ModifyLand_bModifyBlockExtended> bmodifyblockextended;
	public ModifyLand(){}
	public ModifyLand(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
