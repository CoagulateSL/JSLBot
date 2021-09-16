package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.IPADDR;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

import javax.annotation.Nonnull;

public class DataServerLogout_bUserData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public IPADDR vviewerip=new IPADDR();
	@Nonnull
    @Sequence(2)
	public BOOL vdisconnect=new BOOL();
	@Nonnull
    @Sequence(3)
	public LLUUID vsessionid=new LLUUID();
}
