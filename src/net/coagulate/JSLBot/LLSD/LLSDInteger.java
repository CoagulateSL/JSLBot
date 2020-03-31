package net.coagulate.JSLBot.LLSD;

import org.w3c.dom.Node;

import javax.annotation.Nonnull;

/**
 * Represent an Integer in LLSD format.
 *
 * @author Iain Price
 */
public class LLSDInteger extends Atomic {

	int value;

	public LLSDInteger(final int a) {
		value=a;
	}

	LLSDInteger(@Nonnull final Node item) {
		final String str=item.getTextContent();
		if (str.isEmpty()) { return; }
		value=Integer.parseInt(str);
	}

	// ---------- INSTANCE ----------
	@Nonnull
	@Override
	public String toXML(final String lineprefix) {
		return lineprefix+"<integer>"+value+"</integer>\n";
	}

	@Nonnull
	@Override
	public String toString() { return ""+value; }

	public int get() { return value; }
}
