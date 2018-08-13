package net.coagulate.JSLBot.LLSD;

import java.io.IOException;
import org.w3c.dom.Node;

/**
 *
 * @author Iain
 */
public abstract class Atomic {

    static Atomic create(Node item) throws IOException {
        String type=item.getNodeName();
        if (type.equals("string")) { return new LLSDString(item); }
        if (type.equals("map")) { return new LLSDMap(item.getChildNodes()); }
        if (type.equals("integer")) { return new LLSDInteger(item); }
        if (type.equals("boolean")) { return new LLSDBoolean(item); }
        if (type.equals("real")) { return new LLSDReal(item); }
        if (type.equals("array")) { return new LLSDArray(item.getChildNodes()); }
        if (type.equals("uuid")) { return new LLSDUUID(item); }
        if (type.equals("binary")) { return new LLSDBinary(item); }
        if (type.equals("date")) { return new LLSDString(item); }
        if (type.equals("undef")) { return null; }
        throw new IOException("Unknown LLSD type "+type);
    }
    public String toXML() { return toXML(""); }
    public abstract String toXML(String lineprefix);
}
