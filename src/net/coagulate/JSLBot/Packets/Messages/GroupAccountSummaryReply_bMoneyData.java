package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class GroupAccountSummaryReply_bMoneyData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vrequestid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public S32 vintervaldays=new S32();
	@Nonnull
    @Sequence(2)
	public S32 vcurrentinterval=new S32();
	@Nonnull
    @Sequence(3)
	public Variable1 vstartdate=new Variable1();
	@Nonnull
    @Sequence(4)
	public S32 vbalance=new S32();
	@Nonnull
    @Sequence(5)
	public S32 vtotalcredits=new S32();
	@Nonnull
    @Sequence(6)
	public S32 vtotaldebits=new S32();
	@Nonnull
    @Sequence(7)
	public S32 vobjecttaxcurrent=new S32();
	@Nonnull
    @Sequence(8)
	public S32 vlighttaxcurrent=new S32();
	@Nonnull
    @Sequence(9)
	public S32 vlandtaxcurrent=new S32();
	@Nonnull
    @Sequence(10)
	public S32 vgrouptaxcurrent=new S32();
	@Nonnull
    @Sequence(11)
	public S32 vparceldirfeecurrent=new S32();
	@Nonnull
    @Sequence(12)
	public S32 vobjecttaxestimate=new S32();
	@Nonnull
    @Sequence(13)
	public S32 vlighttaxestimate=new S32();
	@Nonnull
    @Sequence(14)
	public S32 vlandtaxestimate=new S32();
	@Nonnull
    @Sequence(15)
	public S32 vgrouptaxestimate=new S32();
	@Nonnull
    @Sequence(16)
	public S32 vparceldirfeeestimate=new S32();
	@Nonnull
    @Sequence(17)
	public S32 vnonexemptmembers=new S32();
	@Nonnull
    @Sequence(18)
	public Variable1 vlasttaxdate=new Variable1();
	@Nonnull
    @Sequence(19)
	public Variable1 vtaxdate=new Variable1();
}
