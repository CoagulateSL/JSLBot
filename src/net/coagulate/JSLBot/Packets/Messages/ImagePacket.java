package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ImagePacket extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 10; }
	public final String getName() { return "ImagePacket"; }
	@Sequence(0)
	public ImagePacket_bImageID bimageid=new ImagePacket_bImageID();
	@Sequence(1)
	public ImagePacket_bImageData bimagedata=new ImagePacket_bImageData();
}
