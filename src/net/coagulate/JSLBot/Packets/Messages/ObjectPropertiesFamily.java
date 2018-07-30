package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectPropertiesFamily extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 10; }
	public final String getName() { return "ObjectPropertiesFamily"; }
	@Sequence(0)
	public ObjectPropertiesFamily_bObjectData bobjectdata=new ObjectPropertiesFamily_bObjectData();
}
