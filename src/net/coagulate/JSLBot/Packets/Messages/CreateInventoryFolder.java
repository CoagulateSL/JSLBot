package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CreateInventoryFolder extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 273; }
	public final String getName() { return "CreateInventoryFolder"; }
	@Sequence(0)
	public CreateInventoryFolder_bAgentData bagentdata=new CreateInventoryFolder_bAgentData();
	@Sequence(1)
	public CreateInventoryFolder_bFolderData bfolderdata=new CreateInventoryFolder_bFolderData();
	public CreateInventoryFolder(){}
	public CreateInventoryFolder(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
