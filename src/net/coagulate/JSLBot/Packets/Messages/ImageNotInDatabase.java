package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ImageNotInDatabase extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 86; }
	public final String getName() { return "ImageNotInDatabase"; }
	@Sequence(0)
	public ImageNotInDatabase_bImageID bimageid=new ImageNotInDatabase_bImageID();
}
