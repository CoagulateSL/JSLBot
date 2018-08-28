package net.coagulate.JSLBot.LLSD;

import org.w3c.dom.Node;

/** Represent an Integer in LLSD format.
 *
 * @author Iain Price
 */
public class LLSDInteger extends Atomic {

    int value=0;
    public LLSDInteger(int a) {
        value=a;
    }

    LLSDInteger(Node item) {
        String str=item.getTextContent();
        if (str.isEmpty()) { return; }
        value=Integer.parseInt(str);
    }

    @Override
    public String toXML(String lineprefix) {
        return lineprefix+"<integer>"+value+"</integer>\n";
    }
    @Override
    public String toString() { return ""+value; }

    public int get() { return value; }
}
