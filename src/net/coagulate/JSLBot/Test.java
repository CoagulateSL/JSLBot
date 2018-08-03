package net.coagulate.JSLBot;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/** Stand alone single bot launcher.
 *
 * @author Iain Price <git@predestined.net>
 */
public class Test {

    /** Launch a singular bot and lose our thread to its AI thread.
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println("JSLBot single-instance wrapper starting up");
        // as far as multiple bots go you have two options if you want to use this config system
        // 1) You can just "sub-space" the configuration namespace; make a new Config(Config,prefix) and write your login values as "prefix.firstname" etc
        // then pass each bot the sub-space of the configuration
        // 2) alternatively you can just make a configuration for each bot from scratch, each in its own store or file.
        // a lot depends on your backing store, the file provider can serve different files to different bots via instansiation, 
        // or separate namespaces of one file to separate bots, via recursive instansiation (see Configuration's constructors)
        if (args.length<1) { System.out.println("Supply config file as parameter"); return; }
        String CONFIGFILE=args[0];
        //initConfig(CONFIGFILE);
        Configuration config=new FileBasedConfiguration(CONFIGFILE);
        //System.out.println("===== Configuration file loaded =====\n"+config.dump());

        new JSLBot(config).run(); // lose control to bot.  call start() to background the bot and continue execution here.
    }
    
    /** Builds a base configuration file */
    static void initConfig(String CONFIGFILE) throws Exception {
        System.out.println("CREATING NEW CONFIGURATION FILE IN 3 SECONDS.  ABORT NOW.");
        Thread.sleep(3000);
        Map<String,String> m=new HashMap<>();
        
        // as a minumum, the bot code its self will attempt to look up
        //
        // firstname
        // lastname
        // password - which may be a plain text value, or the $1$<MD5> format that the web API uses.  naturally we recommend storing the MD5 hash
        //
        // optional but highly recommended, set "owner" to your avatar's UUID.  this value is also read by the bot and used to assume 
        // authorisation by modules that use security permissions... once you are the owner you can then use the instant message interface
        // to configure everything else.
        //
        // also optional but highly recommended, set "handlers" to at least "CnC", without any modules the bot is essentially uncontrollable
        // the CnC modules handles the IM command interface, so you can then add other modules and configure the bot etc from there.
        // NOTE this value is case sensitive and is actually a Java classname
        //
        // Security note: the md5 hash is probably not considered secure any more, and this is an unsalted hash so rainbow tables will destroy this
        // I wouldn't really consider this to be that secure, protect your configuration files as if it were a plaintext password.
        
        m.put("firstname","FIRSTNAME");
        m.put("lastname","LASTNAME");
        m.put("password",BotUtils.md5hash("PASSWORD"));
        m.put("owner","YOURUUID");
        m.put("handlers","CnC");
        
        FileOutputStream fos=new FileOutputStream(CONFIGFILE);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(m);
        oos.close();
        fos.close();
    }

    // something i keep around for reference, its a region restart message.
        /*CnC.parseRegionRestart("<? LLSD/XML ?>\n" +
"<llsd><map><key>MINUTES</key><integer>4</integer><key>NAME</key><string>Cerasi</string></map></llsd>");*/

    
}
