package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MeanCollisionAlert extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 136; }
	public final String getName() { return "MeanCollisionAlert"; }
	@Sequence(0)
	public List<MeanCollisionAlert_bMeanCollision> bmeancollision;
}
