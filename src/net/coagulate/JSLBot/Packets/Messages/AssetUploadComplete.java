package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AssetUploadComplete extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 334; }
	public final String getName() { return "AssetUploadComplete"; }
	@Sequence(0)
	public AssetUploadComplete_bAssetBlock bassetblock=new AssetUploadComplete_bAssetBlock();
}
