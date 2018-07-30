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
    public String toString() { return value; }
}

