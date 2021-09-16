package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S8;

import javax.annotation.Nonnull;

public class AssetUploadComplete_bAssetBlock extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vuuid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public S8 vtype=new S8();
	@Nonnull
    @Sequence(2)
	public BOOL vsuccess=new BOOL();
}
