package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateInventoryFolder extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 274; }
	public final String getName() { return "UpdateInventoryFolder"; }
	@Sequence(0)
	public UpdateInventoryFolder_bAgentData bagentdata=new UpdateInventoryFolder_bAgentData();
	@Sequence(1)
	public List<UpdateInventoryFolder_bFolderData> bfolderdata;
	public UpdateInventoryFolder(){}
	public UpdateInventoryFolder(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
