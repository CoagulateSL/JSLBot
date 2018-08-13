package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ViewerEffect extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 17; }
	public final String getName() { return "ViewerEffect"; }
	@Sequence(0)
	public ViewerEffect_bAgentData bagentdata=new ViewerEffect_bAgentData();
	@Sequence(1)
	public List<ViewerEffect_bEffect> beffect;
	public ViewerEffect(){}
	public ViewerEffect(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
