package net.coagulate.JSLBot;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A configuration provider that only stores values temporarily.
 *
 * @author Iain Price
 */
public class TransientConfiguration extends Configuration {
	// our transient store
	private final Map<String,String> kv=new HashMap<>();

	// ---------- INSTANCE ----------
	@Nullable
	@Override
	public String get(final String param) {
		if (kv.containsKey(param)) { return kv.get(param); }
		return null;
	}

	@Override
	public void put(final String param,
	                final String value) {
		kv.put(param,value);
	}

	@Nonnull
	@Override
	public Set<String> get() {
		return kv.keySet();
	}

	@Nonnull
	@Override
	public String dump() {
		@Nonnull String response="";
		for (@Nonnull final Map.Entry<String,String> entry: kv.entrySet()) {
			if (!"".equals(response)) { response+="\n"; }
			response+=entry.getKey()+"="+entry.getValue();
		}
		return response;
	}

	@Override
	public boolean persistent() { return false; }
}
