package net.coagulate.JSLBot.LLSD;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/** Stores the deconstructed form of an LLSD document.
 * Which is essentially XML with some defined types and the like, sent over HTTPS
 * @author Iain Price
 */
public class LLSD extends Container {
    List<Container> contents=new ArrayList<>();

    /** Build an LLSD object around an existing container.
     * Typically a map, sometimes an array.
     * @param c The container to wrap the LLSD document around
     */
    public LLSD(Container c) { this.contents.add(c); }

    /** Convert a received document into Class form.
     *
     * @param read The XML/LLSD document as a String
     */
    public LLSD(String read) {
        try {
            // blah blah parse it
            DocumentBuilder builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc=builder.parse(new ByteArrayInputStream(read.getBytes(StandardCharsets.UTF_8)));
            // get top of the document (<llsd>...</llsd>)
            Element root=doc.getDocumentElement();
            if (!"llsd".equals(root.getTagName())) { throw new IOException("Response did not commence with LLSD element:"+read); }
            // should be full of (or has at least one, more may or may not be allowed :P) "array" or "map", as per 'container' types
            NodeList nl=root.getChildNodes();
            for (int node=0;node<nl.getLength();node++) {
                Node n=nl.item(node);
                String type=n.getNodeName();
                boolean handled=false;
                if ("array".equals(type)) { contents.add(new LLSDArray(n.getChildNodes())); handled=true; }
                if ("map".equals(type)) { contents.add(new LLSDMap(n.getChildNodes())); handled=true; }
                if ("undef".equals(type)) { handled=true; }
                if (!handled) { throw new AssertionError("Found container of type "+type+" which we don't know about.  Parse error most likely."); }
            }
        } catch (SAXException|ParserConfigurationException|IOException ex) {
            IllegalArgumentException f=new IllegalArgumentException(ex);
            f.initCause(ex);
            throw f;
        }
    }

    /** Format this document as XML/LLSD
     *
     * @param lineprefix Spaces to append to the start of the lines (used for indenting)
     * @return XML Document as String
     */
    @Override
    public String toXML(String lineprefix) {
        String response=lineprefix+"<llsd>\n";
        for (Container c:contents) {
            response+=c.toXML(lineprefix+"  ");
        }
        response+=lineprefix+"</llsd>\n";
        return response;
    }

    /** Convenience method to get the first entry in this container.
     * Mostly used internally for initial processing of the wrapped document.
     * @return The first entry, as a container
     */
    public Container getFirst() {
        if (contents.isEmpty()) { return null; }
        return contents.get(0);
    }
}
