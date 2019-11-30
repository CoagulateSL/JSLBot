package net.coagulate.JSLBot.LLSD;

import org.w3c.dom.Node;

import javax.annotation.Nonnull;

/** Represents a Real in LLSD Format
 *
 * @author Iain Price
 */
public class LLSDReal extends Atomic {

    float value=0;
    public LLSDReal(float a) {
        value=a;
    }

    public LLSDReal(@Nonnull Node item) {
        value=Float.parseFloat(item.getTextContent());
    }

    @Nonnull
    @Override
    public String toXML(String lineprefix) {
        return lineprefix+"<real>"+value+"</real>\n";
    }
    @Nonnull
    @Override
    public String toString() { return ""+value; }

    public float get() { return value; }
}
