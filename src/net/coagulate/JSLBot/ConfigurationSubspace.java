package net.coagulate.JSLBot;

import javax.annotation.Nullable;
import java.util.Set;

/**
 * A configuration "subspace", basically just uses "." separators in the key name.
 *
 * @author Iain Price
 */
public class ConfigurationSubspace extends Configuration {

	final Configuration c;
	final String p;

	public ConfigurationSubspace(final Configuration c,
	                             final String prefix) {
		this.c=c;
		p=prefix;
	}

	// ---------- INSTANCE ----------
	@Nullable
	@Override
	public String get(final String param) {
		return c.get(p+"."+param);
	}

	@Override
	public void put(final String param,
	                final String value) {
		c.put(p+"."+param,value);
	}

	@Override
	public Set<String> get() {
		return getMaster().get();
	}

	@Override
	public String dump() {
		return c.dump();
	}

	@Override
	public Configuration getMaster() {return c;}

	@Override
	public boolean persistent() { return getMaster().persistent(); }

}
