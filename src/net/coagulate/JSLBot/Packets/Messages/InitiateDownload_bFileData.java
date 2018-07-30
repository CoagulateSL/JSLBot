package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class InitiateDownload_bFileData extends Block {
	@Sequence(0)
	public Variable1 vsimfilename=new Variable1();
	@Sequence(1)
	public Variable1 vviewerfilename=new Variable1();
}
