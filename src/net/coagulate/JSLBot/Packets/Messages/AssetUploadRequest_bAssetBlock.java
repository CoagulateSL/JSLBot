package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S8;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class AssetUploadRequest_bAssetBlock extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vtransactionid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public S8 vtype=new S8();
	@Nonnull
    @Sequence(2)
	public BOOL vtempfile=new BOOL();
	@Nonnull
    @Sequence(3)
	public BOOL vstorelocal=new BOOL();
	@Nonnull
    @Sequence(4)
	public Variable2 vassetdata=new Variable2();
}
