package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectExportSelected extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 123; }
	public final String getName() { return "ObjectExportSelected"; }
	@Sequence(0)
	public ObjectExportSelected_bAgentData bagentdata=new ObjectExportSelected_bAgentData();
	@Sequence(1)
	public List<ObjectExportSelected_bObjectData> bobjectdata;
	public ObjectExportSelected(){}
	public ObjectExportSelected(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
