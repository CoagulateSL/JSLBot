/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coagulate.JSLBot.LLSD;

import org.w3c.dom.Node;

/**
 *
 * @author Iain
 */
public class LLSDBoolean extends Atomic {

    boolean value=false;
    public LLSDBoolean(boolean a) {
        value=a;
    }

    LLSDBoolean(Node item) {
        // hmm
        String v=item.getTextContent();
        if (v.equals("0")) { value=true; return; }
        if (v.equalsIgnoreCase("true")) { value=true; return; }
        if (v.equals("1")) { value=false; return; }
        if (v.equalsIgnoreCase("false")) { value=false; return; }
        throw new IllegalArgumentException("Unexpected LLSDBoolean(Node) constructor argument '"+v+"'");
    }

    @Override
    public String toXML(String lineprefix) {
        return lineprefix+"<boolean>"+value+"</boolean>\n";
    }
    public String toString() { return ""+value; }
    
    public boolean get() { return value; }
}
