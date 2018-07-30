package net.coagulate.JSLBot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Implementation of the Configuration system that uses a simple object-store file.
 *
 * @author Iain Price <git@predestined.net>
 */
public class FileBasedConfiguration extends Configuration {

    String filename="";
    Map<String,String> kvstore=new HashMap();
    
    public FileBasedConfiguration(String filename) throws IOException, ClassNotFoundException {
        this.filename=filename;
        FileInputStream fis=new FileInputStream(filename);
        ObjectInputStream ois=new ObjectInputStream(fis);
        kvstore=(Map<String, String>)ois.readObject();
        ois.close();
        fis.close();
    }
    
    @Override
    public String get(String key) {
        if (!kvstore.containsKey(key)) { put(key,null); return null; } // keep track of what was asked for...
        return kvstore.get(key);
    }

    @Override
    public void put(String key, String value) {
        kvstore.put(key,value);
        try {
            writeStore();
        } catch (IOException e) {
            Log.log(null,Log.ERROR,"KVStore write failed, CONFIGURATION NOT SAVED : "+e.toString(),e);
        }
    }
    
    
    private void writeStore() throws FileNotFoundException, IOException {
        FileOutputStream fos=new FileOutputStream(filename);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(kvstore);
        oos.close();
        fos.close();
    }
    
    public String dump() {
        String response="";
        for (String k:kvstore.keySet()) {
            if (!response.equals("")) { response+="\n"; }
            response+=k+"="+kvstore.get(k); 
        }
        return response;
    }
}
