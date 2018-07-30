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
        value=Boolean.parseBoolean(item.getTextContent());
    }

    @Override
    public String toXML(String lineprefix) {
        return lineprefix+"<boolean>"+value+"</boolean>\n";
    }
    public String toString() { return ""+value; }
    
    public boolean get() { return value; }
}
