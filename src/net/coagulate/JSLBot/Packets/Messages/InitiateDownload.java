package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class InitiateDownload extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 403; }
	public final String getName() { return "InitiateDownload"; }
	@Sequence(0)
	public InitiateDownload_bAgentData bagentdata=new InitiateDownload_bAgentData();
	@Sequence(1)
	public InitiateDownload_bFileData bfiledata=new InitiateDownload_bFileData();
}
