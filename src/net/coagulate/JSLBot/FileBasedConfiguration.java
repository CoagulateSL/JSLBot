package net.coagulate.JSLBot;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of the Configuration system that uses a simple object-store file.
 * We should probably change this to a text file k=v "ini" style format
 *
 * @author Iain Price
 */
public class FileBasedConfiguration extends Configuration {
	
	@Nonnull final String filename;
	@Nonnull Map<String,String> kvstore;
	
	@SuppressWarnings("unchecked") // pretty stuck with this :)
	public FileBasedConfiguration(@Nonnull final String filename) {
		this.filename=filename;
		try (@Nonnull final FileInputStream fis=new FileInputStream(filename);
		     @Nonnull final ObjectInputStream ois=new ObjectInputStream(fis)) {
			kvstore=(Map<String,String>)ois.readObject();
		} catch (@Nonnull final IOException|ClassNotFoundException e) {
			throw new AssertionError("Could not load configuration file",e);
		}
	}
	
	// ---------- INSTANCE ----------
	@Nullable
	@Override
	public String get(final String key) {
		if (!kvstore.containsKey(key)) {
			put(key,null);
			return null;
		} // keep track of what was asked for...
		return kvstore.get(key);
	}
	
	@Override
	@SuppressWarnings("CallToPrintStackTrace")
	public void put(final String key,final String value) {
		kvstore.put(key,value);
		try {
			writeStore();
		} catch (@Nonnull final IOException e) {
			System.err.println("KVStore write failed, CONFIGURATION NOT SAVED : "+e);
			e.printStackTrace();
		}
	}
	
	@Nonnull
	@Override
	public Set<String> get() {
		return kvstore.keySet();
	}
	
	@Nonnull
	@Override
	public String dump() {
		@Nonnull final StringBuilder response=new StringBuilder();
		for (@Nonnull final Map.Entry<String,String> entry: kvstore.entrySet()) {
			if (!response.isEmpty()) {
				response.append("\n");
			}
			response.append(entry.getKey()).append("=").append(entry.getValue());
		}
		return response.toString();
	}
	
	@Override
	public boolean persistent() {
		return true;
	}
	
	// ----- Internal Instance -----
	private void writeStore() throws IOException {
		try (@Nonnull final FileOutputStream fos=new FileOutputStream(filename);
		     @Nonnull final ObjectOutputStream oos=new ObjectOutputStream(fos)) {
			oos.writeObject(kvstore);
		}
	}
}
