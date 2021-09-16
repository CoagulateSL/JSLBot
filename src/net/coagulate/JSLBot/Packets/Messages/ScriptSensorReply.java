package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ScriptSensorReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 248; }
	@Nonnull
    public final String getName() { return "ScriptSensorReply"; }
	@Nonnull
    @Sequence(0)
	public ScriptSensorReply_bRequester brequester=new ScriptSensorReply_bRequester();
	@Sequence(1)
	public List<ScriptSensorReply_bSensedData> bsenseddata;
}
