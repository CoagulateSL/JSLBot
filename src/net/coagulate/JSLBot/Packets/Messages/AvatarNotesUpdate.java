package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarNotesUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 177; }
	public final String getName() { return "AvatarNotesUpdate"; }
	@Sequence(0)
	public AvatarNotesUpdate_bAgentData bagentdata=new AvatarNotesUpdate_bAgentData();
	@Sequence(1)
	public AvatarNotesUpdate_bData bdata=new AvatarNotesUpdate_bData();
}
