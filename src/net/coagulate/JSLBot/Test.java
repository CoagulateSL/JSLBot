package net.coagulate.JSLBot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
        if (!(new File(CONFIGFILE).exists())) {initConfig(CONFIGFILE);}
        Configuration config=new FileBasedConfiguration(CONFIGFILE);
        //System.out.println("===== Configuration file loaded =====\n"+config.dump());

        if (args.length>=2) {
            System.out.println("*** SECURITY NOTE *** REPLACING CnC.authorisation.owneruuid with "+args[1]);
            config.put("CnC.authorisation.owneruuid", args[1]);
        }
        if (args.length>=3) {
            System.out.println("*** SECURITY NOTE *** REPLACING CnC.authorisation.ownerusername with "+args[2]);
            config.put("CnC.authorisation.ownerusername", args[2]);
        }
        
        new JSLBot(config).run(); // lose control to bot.  call start() to background the bot and continue execution here.
    }
    
    /** Builds a base configuration file */
    static void initConfig(String CONFIGFILE) throws Exception {
        Map<String,String> m=new HashMap<>();
        
        System.out.println("---- ALERT ----\nConfiguration file '"+CONFIGFILE+"' does not exist.\nIf you complete this process it will be created\n\n");
        Scanner in=new Scanner(System.in);
        
        System.out.print("Bot's first name: "); String firstname=in.nextLine();
        System.out.print("Bot's last name: "); String lastname=in.nextLine();
        System.out.print("Bot's password: "); String password=in.nextLine();
        System.out.println("Handlers: CnC,Sink,Health,Regions,Teleportation,Agent,Objects,Groups");
        System.out.println("Login Location: home");
        System.out.println("Authoriser: OwnerOnly");
        System.out.print("Owner UUID: ");String owneruuid=in.nextLine();
        System.out.println("\nCreating initial configuration file ...");

        m.put("firstname",firstname);
        m.put("lastname",lastname);
        m.put("CnC.authoriser","OwnerOnly");
        m.put("handlers","CnC,Sink,Health,Regions,Teleportation,Agent,Objects,Groups");
        m.put("loginlocation","home");
        m.put("CnC.authorisation.owneruuid",owneruuid);
        m.put("password",BotUtils.md5hash(password));

        FileOutputStream fos=new FileOutputStream(CONFIGFILE);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(m);
        oos.close();
        fos.close();
        
        System.out.println("Configuration file created, initiating bot startup\n\n");
    }

    // something i keep around for reference, its a region restart message.
        /*CnC.parseRegionRestart("<? LLSD/XML ?>\n" +
"<llsd><map><key>MINUTES</key><integer>4</integer><key>NAME</key><string>Cerasi</string></map></llsd>");*/

    
}
