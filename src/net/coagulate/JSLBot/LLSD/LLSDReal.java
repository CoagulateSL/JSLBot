package net.coagulate.JSLBot.LLSD;

import org.w3c.dom.Node;

/** Represents a Real in LLSD Format
 *
 * @author Iain Price
 */
public class LLSDReal extends Atomic {

    float value=0;
    public LLSDReal(float a) {
        value=a;
    }

    public LLSDReal(Node item) {
        value=Float.parseFloat(item.getTextContent());
    }

    @Override
    public String toXML(String lineprefix) {
        return lineprefix+"<real>"+value+"</real>\n";
    }
    @Override
    public String toString() { return ""+value; }

    public float get() { return value; }
}
