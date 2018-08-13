package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CameraConstraint extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 22; }
	public final String getName() { return "CameraConstraint"; }
	@Sequence(0)
	public CameraConstraint_bCameraCollidePlane bcameracollideplane=new CameraConstraint_bCameraCollidePlane();
}
