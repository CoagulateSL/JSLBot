package net.coagulate.JSLBot.LLSD;

import org.w3c.dom.Node;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Superclass for data containers in LLSD.
 *
 * @author Iain Price
 */
public abstract class Atomic {

	// ----- Internal Statics -----
	@Nullable
	static Atomic create(@Nonnull final Node item) {
		final String type=item.getNodeName();
		if ("string".equals(type)) { return new LLSDString(item); }
		if ("map".equals(type)) { return new LLSDMap(item.getChildNodes()); }
		if ("integer".equals(type)) { return new LLSDInteger(item); }
		if ("boolean".equals(type)) { return new LLSDBoolean(item); }
		if ("real".equals(type)) { return new LLSDReal(item); }
		if ("array".equals(type)) { return new LLSDArray(item.getChildNodes()); }
		if ("uuid".equals(type)) { return new LLSDUUID(item); }
		if ("binary".equals(type)) { return new LLSDBinary(item); }
		if ("date".equals(type)) { return new LLSDString(item); }
		if ("undef".equals(type)) { return null; }
		throw new AssertionError("Unknown LLSD type "+type);
	}

	// ---------- INSTANCE ----------
	@Nonnull
	public String toXML() { return toXML(""); }

	@Nonnull
	public abstract String toXML(String lineprefix);
}
