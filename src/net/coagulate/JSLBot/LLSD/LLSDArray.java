package net.coagulate.JSLBot.LLSD;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents an Array in LLSD format
 *
 * @author Iain Price
 */
public class LLSDArray extends Container implements Iterable<Atomic> {
	private final List<Atomic> data=new ArrayList<>();

	public LLSDArray() {}

	public LLSDArray(@Nonnull final NodeList nodes) {
		for (int i=0;i<nodes.getLength();i++) {
			final Node n=nodes.item(i);
			final Atomic atom=Atomic.create(n);
			if (atom!=null) { data.add(atom); }
		}
	}

	// ---------- INSTANCE ----------
	public void add(final String s) { data.add(new LLSDString(s)); }

	public void add(final Atomic a) { data.add(a); }

	@Nonnull
	public List<Atomic> get() { return data; }

	@Nonnull
	@Override
	public String toXML(final String prefix) {
		final StringBuilder resp=new StringBuilder(prefix+"<array>\n");
		for (final Atomic a: data) { resp.append(a.toXML(prefix+"  ")); }
		resp.append(prefix).append("</array>\n");
		return resp.toString();
	}

	@Override
	@Nonnull
	public Iterator<Atomic> iterator() {
		return data.iterator();
	}
}
