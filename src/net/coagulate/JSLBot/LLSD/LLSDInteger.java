package net.coagulate.JSLBot.LLSD;

import org.w3c.dom.Node;

/**
 *
 * @author Iain
 */
public class LLSDInteger extends Atomic {

    int value=0;
    public LLSDInteger(int a) {
        value=a;
    }

    LLSDInteger(Node item) {
        value=Integer.parseInt(item.getTextContent());
    }

    @Override
    public String toXML(String lineprefix) {
        return lineprefix+"<integer>"+value+"</integer>\n";
    }
    public String toString() { return ""+value; }

    public int get() { return value; }
}
