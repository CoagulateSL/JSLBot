package net.coagulate.JSLBot;

import net.coagulate.JSLBot.LLSD.Atomic;
import net.coagulate.JSLBot.LLSD.LLSDMap;

import javax.annotation.Nonnull;

/** Encapsulates an EventQueue XML document as an event.
 *
 * @author Iain Price
 */
public class XMLEvent extends Event {
    private final Atomic content;
    public Atomic body() { return content; }

    public XMLEvent(@Nonnull final JSLBot bot, @Nonnull final Regional r, final Atomic content, @Nonnull final String name) {
        super(bot, r,name);
        this.content=content;
    }

    @Nonnull
    @Override
    public String dump() {
        return body().toXML();
    }

    @Nonnull
    public LLSDMap map() { // "dangerous", assumes the body is a map, which it usually is.
        return (LLSDMap)body();
    }

    
}
