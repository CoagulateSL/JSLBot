package net.coagulate.JSLBot.LLSD;

import org.w3c.dom.Node;

/** Represents a String in LLSD format
 *
 * @author Iain Price
 */
public class LLSDString extends Atomic {

    String value="";
    public LLSDString(String s) {
        value=s;
    }

    LLSDString(Node item) {
        value=item.getTextContent();
    }

    @Override
    public String toXML(String lineprefix) {
        return lineprefix+"<string>"+value+"</string>\n";
    }
    @Override
    public String toString() { return value; }
}

