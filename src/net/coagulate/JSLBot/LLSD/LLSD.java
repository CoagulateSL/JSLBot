package net.coagulate.JSLBot.LLSD;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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

/**
 *
 * @author Iain
 */
public class LLSD extends Container {
    List<Container> contents=new ArrayList<>();

    public LLSD(Container c) { this.contents.add(c); }

    // so many ways of doing things in this protocol..
    // yup, custom XML over HTTP over viewer's polling servers constantly.  some evolution from UDP.
    public LLSD(String read) throws IOException {
        try {
            // blah blah parse it
            DocumentBuilder builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc=builder.parse(new ByteArrayInputStream(read.getBytes("UTF-8")));
            // get top of the document (<llsd>...</llsd>)
            Element root=doc.getDocumentElement();
            if (!root.getTagName().equals("llsd")) { throw new IOException("Response did not commence with LLSD element:"+read); }
            // should be full of (or has at least one, more may or may not be allowed :P) "array" or "map", as per 'container' types
            NodeList nl=root.getChildNodes();
            for (int node=0;node<nl.getLength();node++) {
                Node n=nl.item(node);
                String type=n.getNodeName();
                boolean handled=false;
                if (type.equals("array")) { contents.add(new LLSDArray(n.getChildNodes())); handled=true; }
                if (type.equals("map")) { contents.add(new LLSDMap(n.getChildNodes())); handled=true; }
                if (type.equals("undef")) { handled=true; }
                if (!handled) { throw new IOException("Found container of type "+type+" which we don't know about.  Parse error most likely."); }
            }
        } catch (SAXException|ParserConfigurationException ex) {
            IOException f=new IOException(ex);
            f.initCause(ex);
            throw f;
        }
    }
    @Override
    public String toXML(String lineprefix) {
        String response=lineprefix+"<llsd>\n";
        for (Container c:contents) {
            response+=c.toXML(lineprefix+"  ");
        }
        response+=lineprefix+"</llsd>\n";
        return response;
    }

    public Container getFirst() {
        if (contents.isEmpty()) { return null; }
        return contents.get(0);
    }
}
