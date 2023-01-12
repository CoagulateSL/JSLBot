package net.coagulate.JSLBot.LLSD;

import org.w3c.dom.NodeList;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A Map container in LLSD.
 *
 * @author Iain Price
 */
public class LLSDMap extends Container {
	private final Map<String,Atomic> data=new HashMap<>();
	
	/**
	 * Create an LLSD Map from a list of XML nodes.
	 *
	 * @param nodes NodeList contents of this map
	 */
	public LLSDMap(@Nonnull final NodeList nodes) {
		for (int i=0;i<nodes.getLength();i+=2) {
			final String key=nodes.item(i).getFirstChild().getNodeValue();
			@Nullable final Atomic a=Atomic.create(nodes.item(i+1));
			if (a!=null) {
				data.put(key,a);
			}
		}
	}
	
	public LLSDMap() {
	}
	
	
	// ---------- INSTANCE ----------
	@Nonnull
	@Override
	public String toXML(final String lineprefix) {
		@Nonnull final StringBuilder resp=new StringBuilder(lineprefix+"<map>\n");
		for (@Nonnull final Map.Entry<String,Atomic> entry: data.entrySet()) {
			resp.append(lineprefix).append("<key>").append(entry.getKey()).append("</key>\n");
			resp.append(entry.getValue().toXML(lineprefix+"  "));
		}
		resp.append(lineprefix).append("</map>\n");
		return resp.toString();
	}
	
	@Nonnull
	public Set<String> keys() {
		return data.keySet();
	}
	
	public boolean containsKey(final String key) {
		return data.containsKey(key);
	}
	
	public Atomic get(final String key) {
		return data.get(key);
	}
	
	/**
	 * Get a key, with a default value if not present.
	 *
	 * @param key Key to get
	 * @param def Default atomic to return
	 * @return The atomic from the map, or the default if not present.
	 */
	public Atomic get(final String key,final Atomic def) {
		return data.getOrDefault(key,def);
	}
	
	public void put(final String ack,final Atomic atom) {
		data.put(ack,atom);
	}
}
