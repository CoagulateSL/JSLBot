package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ImageData extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 9; }
	public final String getName() { return "ImageData"; }
	@Sequence(0)
	public ImageData_bImageID bimageid=new ImageData_bImageID();
	@Sequence(1)
	public ImageData_bImageData bimagedata=new ImageData_bImageData();
}
