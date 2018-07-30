package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AssetUploadRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 333; }
	public final String getName() { return "AssetUploadRequest"; }
	@Sequence(0)
	public AssetUploadRequest_bAssetBlock bassetblock=new AssetUploadRequest_bAssetBlock();
}
