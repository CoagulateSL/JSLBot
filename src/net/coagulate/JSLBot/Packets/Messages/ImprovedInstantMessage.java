package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class ImprovedInstantMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 254; }
	@Nonnull
    public final String getName() { return "ImprovedInstantMessage"; }
	@Nonnull
    @Sequence(0)
	public ImprovedInstantMessage_bAgentData bagentdata=new ImprovedInstantMessage_bAgentData();
	@Nonnull
    @Sequence(1)
	public ImprovedInstantMessage_bMessageBlock bmessageblock=new ImprovedInstantMessage_bMessageBlock();
	@Nonnull
    @Sequence(2)
	public ImprovedInstantMessage_bEstateBlock bestateblock=new ImprovedInstantMessage_bEstateBlock();
	public ImprovedInstantMessage(){}
	public ImprovedInstantMessage(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}

	public enum InstantMessageDialog
	{
		MessageFromAgent(0),
		MessageBox(1),
		GroupInvitation(3),
		InventoryOffered(4),
		InventoryAccepted(5),
		InventoryDeclined(6),
		GroupVote(7),
		TaskInventoryOffered(9),
		TaskInventoryAccepted(10),
		TaskInventoryDeclined(11),
		NewUserDefault(12),
		SessionAdd(13),
		SessionOfflineAdd(14),
		SessionGroupStart(15),
		SessionCardlessStart(16),
		SessionSend(17),
		SessionDrop(18),
		MessageFromObject(19),
		BusyAutoResponse(20),
		ConsoleAndChatHistory(21),
		RequestTeleport(22),
		AcceptTeleport(23),
		DenyTeleport(24),
		GodLikeRequestTeleport(25),
		RequestLure(26),
		GotoUrl(28),
		Session911Start(29),
		Lure911(30),
		FromTaskAsAlert(31),
		GroupNotice(32),
		GroupNoticeInventoryAccepted(33),
		GroupNoticeInventoryDeclined(34),
		GroupInvitationAccept(35),
		GroupInvitationDecline(36),
		GroupNoticeRequested(37),
		FriendshipOffered(38),
		FriendshipAccepted(39),
		FriendshipDeclined(40),
		StartTyping(41),
		StopTyping(42);
		private U8 value;
		private InstantMessageDialog(int value) {
			this.value = new U8(value);
		}
		public U8 getValue() { return value; }
	}

}
