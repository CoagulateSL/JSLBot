package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AssetUploadRequest_bAssetBlock extends Block {
	@Sequence(0)
	public LLUUID vtransactionid=new LLUUID();
	@Sequence(1)
	public S8 vtype=new S8();
	@Sequence(2)
	public BOOL vtempfile=new BOOL();
	@Sequence(3)
	public BOOL vstorelocal=new BOOL();
	@Sequence(4)
	public Variable2 vassetdata=new Variable2();
}
