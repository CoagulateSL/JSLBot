package net.coagulate.JSLBot.LLSD;

import org.w3c.dom.Node;

import javax.annotation.Nonnull;

/**
 * Represents a String in LLSD format
 *
 * @author Iain Price
 */
public class LLSDString extends Atomic {
	
	final String value;
	
	public LLSDString(final String s) {
		value=s;
	}
	
	LLSDString(@Nonnull final Node item) {
		value=item.getTextContent();
	}
	
	// ---------- INSTANCE ----------
	@Nonnull
	@Override
	public String toXML(final String lineprefix) {
		return lineprefix+"<string>"+value+"</string>\n";
	}
	
	@Override
	public String toString() {
		return value;
	}
}

