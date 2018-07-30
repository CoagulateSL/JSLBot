package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ReportAutosaveCrash_bAutosaveData extends Block {
	@Sequence(0)
	public S32 vpid=new S32();
	@Sequence(1)
	public S32 vstatus=new S32();
}
