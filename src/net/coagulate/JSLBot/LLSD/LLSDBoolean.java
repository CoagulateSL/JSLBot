package net.coagulate.JSLBot.LLSD;

import org.w3c.dom.Node;

/** Represent a boolean in LLSD format.
 *
 * @author Iain Price
 */
public class LLSDBoolean extends Atomic {

    boolean value=false;
    public LLSDBoolean(boolean a) {
        value=a;
    }

    LLSDBoolean(Node item) {
        // hmm
        String v=item.getTextContent();
        if ("0".equals(v)) { value=true; return; }
        if ("true".equalsIgnoreCase(v)) { value=true; return; }
        if ("1".equals(v)) { value=false; return; }
        if ("false".equalsIgnoreCase(v)) { value=false; return; }
        throw new AssertionError("Unexpected LLSDBoolean(Node) constructor argument '"+v+"'");
    }

    @Override
    public String toXML(String lineprefix) {
        return lineprefix+"<boolean>"+value+"</boolean>\n";
    }
    @Override
    public String toString() { return ""+value; }
    
    public boolean get() { return value; }
}
