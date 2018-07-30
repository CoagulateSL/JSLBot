package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AvatarPropertiesUpdate_bPropertiesData extends Block {
	@Sequence(0)
	public LLUUID vimageid=new LLUUID();
	@Sequence(1)
	public LLUUID vflimageid=new LLUUID();
	@Sequence(2)
	public Variable2 vabouttext=new Variable2();
	@Sequence(3)
	public Variable1 vflabouttext=new Variable1();
	@Sequence(4)
	public BOOL vallowpublish=new BOOL();
	@Sequence(5)
	public BOOL vmaturepublish=new BOOL();
	@Sequence(6)
	public Variable1 vprofileurl=new Variable1();
}
