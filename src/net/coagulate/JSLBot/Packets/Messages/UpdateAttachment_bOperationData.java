package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateAttachment_bOperationData extends Block {
	@Sequence(0)
	public BOOL vadditem=new BOOL();
	@Sequence(1)
	public BOOL vuseexistingasset=new BOOL();
}
