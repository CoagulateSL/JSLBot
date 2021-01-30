package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarAppearance_bAppearanceData extends Block {
	@Sequence(0)
	public U8 vappearanceversion=new U8();
	@Sequence(1)
	public S32 vcofversion=new S32();
	@Sequence(2)
	public U32 vflags=new U32();
}
