package net.coagulate.JSLBot;

import net.coagulate.JSLBot.LLSD.Atomic;
import net.coagulate.JSLBot.LLSD.LLSDMap;

/**
 *
 * @author iain
 */
public class XMLEvent extends Event {
    private Atomic content;
    public Atomic body() { return content; }
    private String name;
    @Override
    public String getName() {return name;}

    public XMLEvent(JSLBot bot,Regional r,Atomic content,String name) {
        super(bot, r);
        this.content=content;
        this.name=name;
    }

    @Override
    public String dump() {
        return body().toXML();
    }

    public LLSDMap map() { // "dangerous"
        return (LLSDMap)body();
    }

    
}
