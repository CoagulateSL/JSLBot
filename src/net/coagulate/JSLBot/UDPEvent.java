package net.coagulate.JSLBot;

import net.coagulate.JSLBot.Packets.Message;

/** Encapsulate a received UDP message packet as an Event.
 * There are some issues with trying to have packets extend events, since they once extended something else.
 *
 * @author Iain Price <git@predestined.net>
 */
public class UDPEvent extends Event {
    private Message content;
    public Message body() { return content; }

    public UDPEvent(JSLBot bot,Regional r,Message content,String name) {
        super(bot, r,name);
        this.content=content;
    }

    @Override
    public String dump() {
        return body().dump();
    }
}
