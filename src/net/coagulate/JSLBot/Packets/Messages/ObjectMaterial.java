package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectMaterial extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 97; }
	@Nonnull
    public final String getName() { return "ObjectMaterial"; }
	@Nonnull
    @Sequence(0)
	public ObjectMaterial_bAgentData bagentdata=new ObjectMaterial_bAgentData();
	@Sequence(1)
	public List<ObjectMaterial_bObjectData> bobjectdata;
	public ObjectMaterial(){}
	public ObjectMaterial(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
