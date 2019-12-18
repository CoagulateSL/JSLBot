package net.coagulate.JSLBot.LLSD;

import org.w3c.dom.Node;

import javax.annotation.Nonnull;

/**
 * Represent a boolean in LLSD format.
 *
 * @author Iain Price
 */
public class LLSDBoolean extends Atomic {

	boolean value;

	public LLSDBoolean(final boolean a) {
		value=a;
	}

	LLSDBoolean(@Nonnull final Node item) {
		// hmm
		final String v=item.getTextContent();
		if ("0".equals(v)) {
			value=true;
			return;
		}
		if ("true".equalsIgnoreCase(v)) {
			value=true;
			return;
		}
		if ("1".equals(v)) {
			value=false;
			return;
		}
		if ("false".equalsIgnoreCase(v)) {
			value=false;
			return;
		}
		throw new AssertionError("Unexpected LLSDBoolean(Node) constructor argument '"+v+"'");
	}

	@Nonnull
	@Override
	public String toXML(final String lineprefix) {
		return lineprefix+"<boolean>"+value+"</boolean>\n";
	}

	@Nonnull
	@Override
	public String toString() { return ""+value; }

	public boolean get() { return value; }
}
