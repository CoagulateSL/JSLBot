/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coagulate.JSLBot.LLSD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Iain
 */
public class LLSDMap extends Container {
    private Map<String,Atomic> data=new HashMap<>();
    
    public LLSDMap(NodeList nodes) throws IOException {
        for (int i=0;i<nodes.getLength();i+=2) {
            String key=nodes.item(i).getFirstChild().getNodeValue();
            Atomic a=Atomic.create(nodes.item(i+1));
            data.put(key, a);
        }
    }

    public LLSDMap() {
    }
    
    
    public String toXML(String prefix) {
        String resp=prefix+"<map>\n";
        for (String key:data.keySet()) {
            resp+=prefix+"<key>"+key+"</key>\n";
            resp+=data.get(key).toXML(prefix+"  ");
        }
        resp+=prefix+"</map>\n";
        return resp;
    }

    public boolean containsKey(String key) {
        return data.containsKey(key);
    }
    public Atomic get(String key) { return data.get(key); }

    public void put(String ack, Atomic atom) {
        data.put(ack,atom);
    }
}
