package net.coagulate.JSLBot;

import net.coagulate.JSLBot.Packets.Message;

/**
 *
 * @author iain
 */
public class UDPEvent extends Event {
    private Message content;
    public Message body() { return content; }
    private String name;
    @Override
    public String getName() {return name;}

    public UDPEvent(JSLBot bot,Regional r,Message content,String name) {
        super(bot, r);
        this.content=content;
        this.name=name;
    }

    @Override
    public String dump() {
        return body().dump();
    }
}
