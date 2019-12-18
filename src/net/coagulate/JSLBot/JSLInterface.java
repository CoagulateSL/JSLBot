package net.coagulate.JSLBot;

import java.util.HashMap;
import java.util.Map;

/**
 * JSL Public "API" (the useful part that does all the work for you).
 *
 * @author Iain Price
 */
public class JSLInterface {
	private final JSLBot bot;

	JSLInterface(final JSLBot bot) { this.bot=bot; }

	/**
	 * Send and wait for an instant message to be sent.
	 *
	 * @param uuid    User UUID to send message to
	 * @param message Message to send
	 */
	public void instantMessage(final String uuid,
	                           final String message)
	{
		bot.waitConnection(15000);
		final Map<String,String> cmd=new HashMap<>();
		cmd.put("uuid",uuid);
		cmd.put("message",message);
		new CommandEvent(bot,bot.getRegional(),"im",cmd,null).submitAndWait();
	}

	/**
	 * Invite a user to a group (Everybody role)
	 *
	 * @param avataruuid UUID of avatar to invite
	 * @param groupuuid  UUID of group to invite to
	 */
	public void groupInvite(final String avataruuid,
	                        final String groupuuid)
	{
		bot.waitConnection(15000);
		groupInvite(avataruuid,groupuuid,null);
	}

	/**
	 * Invite a user to a group and role
	 *
	 * @param avataruuid User UUID to invite
	 * @param groupuuid  Group UUID to invite to
	 * @param roleuuid   Role UUID to invite to
	 */
	public void groupInvite(final String avataruuid,
	                        final String groupuuid,
	                        final String roleuuid)
	{
		bot.waitConnection(15000);
		final Map<String,String> cmd=new HashMap<>();
		cmd.put("avataruuid",avataruuid);
		cmd.put("groupuuid",groupuuid);
		cmd.put("roleuuid",roleuuid);
		new CommandEvent(bot,bot.getRegional(),"groupInvite",cmd,null).submitAndWait();
	}

	/**
	 * Eject a user from a group.
	 *
	 * @param avataruuid User UUID to eject from group
	 * @param groupuuid  Group UUID to eject from
	 */
	public void groupEject(final String avataruuid,
	                       final String groupuuid)
	{
		bot.waitConnection(15000);
		final Map<String,String> cmd=new HashMap<>();
		cmd.put("avataruuid",avataruuid);
		cmd.put("groupuuid",groupuuid);
		//cmd.put("roleuuid",roleuuid);
		new CommandEvent(bot,bot.getRegional(),"groupEject",cmd,null).submitAndWait();
	}

	/**
	 * Collect a group roster from the server.
	 * Results must be queried from the group module.
	 *
	 * @param groupuuid Group UUID to retrieve.
	 */
	public void groupRoster(final String groupuuid) {
		bot.waitConnection(15000);
		final Map<String,String> cmd=new HashMap<>();
		cmd.put("uuid",groupuuid);
		new CommandEvent(bot,bot.getRegional(),"groupRoster",cmd,null).submitAndWait();
	}


}
