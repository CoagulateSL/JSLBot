package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AssetUploadComplete_bAssetBlock extends Block {
	@Sequence(0)
	public LLUUID vuuid=new LLUUID();
	@Sequence(1)
	public S8 vtype=new S8();
	@Sequence(2)
	public BOOL vsuccess=new BOOL();
}
