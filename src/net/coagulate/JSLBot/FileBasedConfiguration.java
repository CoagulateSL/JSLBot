package net.coagulate.JSLBot;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** Implementation of the Configuration system that uses a simple object-store file.
 * We should probably change this to a text file k=v "ini" style format 
 * @author Iain Price
 */
public class FileBasedConfiguration extends Configuration {

    @Nonnull
    String filename="";
    @Nonnull
    Map<String,String> kvstore=new HashMap<>();
    
    @SuppressWarnings("unchecked") // pretty stuck with this :)
    public FileBasedConfiguration(@Nonnull String filename) {
        this.filename=filename;
        try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis)) {
            kvstore=(Map<String, String>)ois.readObject();
        } catch (@Nonnull IOException|ClassNotFoundException e) {
            throw new AssertionError("Could not load configuration file",e);
        }
    }
    
    @Nullable
    @Override
    public String get(String key) {
        if (!kvstore.containsKey(key)) { put(key,null); return null; } // keep track of what was asked for...
        return kvstore.get(key);
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void put(String key, String value) {
        kvstore.put(key,value);
        try {
            writeStore();
        } catch (IOException e) {
            System.err.println("KVStore write failed, CONFIGURATION NOT SAVED : "+e.toString());
            e.printStackTrace();
        }
    }
    
    
    private void writeStore() throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(kvstore);
        }
    }
    
    @Nonnull
    @Override
    public String dump() {
        String response="";
        for (Map.Entry<String, String> entry : kvstore.entrySet()) {
            if (!"".equals(response)) { response+="\n"; }
            response+= entry.getKey() +"="+ entry.getValue();
        }
        return response;
    }

    @Nonnull
    @Override
    public Set<String> get() {
        return kvstore.keySet();
    }

    @Override
    public boolean persistent() { return true; }
}
