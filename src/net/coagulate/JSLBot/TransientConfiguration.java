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
    public String get(final String key) {
        if (kv.containsKey(key)) {
            return kv.get(key);
        }
        return null;
    }

    @Override
    public void put(final String key,
                    final String value) {
        kv.put(key, value);
    }

	@Nonnull
	@Override
	public Set<String> get() {
		return kv.keySet();
	}

	@Nonnull
	@Override
	public String dump() {
		@Nonnull StringBuilder response= new StringBuilder();
		for (@Nonnull final Map.Entry<String,String> entry: kv.entrySet()) {
			if (response.length() > 0) {
				response.append("\n");
			}
			response.append(entry.getKey()).append("=").append(entry.getValue());
		}
		return response.toString();
	}

	@Override
	public boolean persistent() { return false; }
}
