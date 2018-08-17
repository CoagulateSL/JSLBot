package net.coagulate.JSLBot;

import net.coagulate.JSLBot.LLSD.Atomic;
import net.coagulate.JSLBot.LLSD.LLSDMap;

/** Encapsulates an EventQueue XML document as an event.
 *
 * @author Iain Price
 */
public class XMLEvent extends Event {
    private final Atomic content;
    public Atomic body() { return content; }

    public XMLEvent(JSLBot bot,Regional r,Atomic content,String name) {
        super(bot, r,name);
        this.content=content;
    }

    @Override
    public String dump() {
        return body().toXML();
    }

    public LLSDMap map() { // "dangerous", assumes the body is a map, which it usually is.
        return (LLSDMap)body();
    }

    
}
