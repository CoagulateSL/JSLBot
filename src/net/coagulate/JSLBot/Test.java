package net.coagulate.JSLBot;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/** Stand alone single bot launcher.
 *
 * @author Iain Price
 */
public class Test {

    /** Launch a singular bot using a config store and lose our thread to its AI thread.
     * @param args the command line arguments
     */
    public static void main(@Nonnull final String[] args) {
        System.out.println("JSLBot single-instance wrapper starting up");
        // as far as multiple bots go you have two options if you want to use this config system
        // 1) You can just "sub-space" the configuration namespace; make a new Config(Config,prefix) and write your login values as "prefix.firstname" etc
        // then pass each bot the sub-space of the configuration
        // 2) alternatively you can just make a configuration for each bot from scratch, each in its own store or file.
        // a lot depends on your backing store, the file provider can serve different files to different bots via instansiation, 
        // or separate namespaces of one file to separate bots, via recursive instansiation (see Configuration's constructors)
        String CONFIGFILE=null;
        if (args.length>0) { CONFIGFILE = args[0]; }
        else {
            final Scanner in=new Scanner(System.in);
            System.out.print("Name of config file: "); CONFIGFILE=in.nextLine();
        }
        if (CONFIGFILE==null || !(new File(CONFIGFILE).exists())) {initConfig(CONFIGFILE);}
        final Configuration config=new FileBasedConfiguration(CONFIGFILE);
        //System.out.println("===== Configuration file loaded =====\n"+config.dump());
        final JSLBot bot=new JSLBot(config);
        bot.ALWAYS_RECONNECT=true; // likely this will be cleaned up, but for testing...
        //noinspection CallToThreadRun
        bot.run(); // lose control to bot.  call start() to background the bot and continue execution here.
    }
    
    /** Builds a base configuration file */
    static void initConfig(@Nonnull final String CONFIGFILE) {
        final Map<String,String> m=new HashMap<>();

        System.out.println("---- ALERT ----\nConfiguration file '"+CONFIGFILE+"' does not exist.\nIf you complete this process it will be created\n\n");
        final Scanner in=new Scanner(System.in);

        System.out.print("Bot's first name: "); final String firstname=in.nextLine();
        System.out.print("Bot's last name: "); final String lastname=in.nextLine();
        System.out.print("Bot's password: "); final String password=in.nextLine();
        System.out.println("Handlers: CnC,Sink,Health,Regions,Teleportation,Agent,Objects,Groups,Inventory");
        System.out.println("Login Location: home");
        System.out.println("Authoriser: OwnerOnly");
        System.out.print("Owner UUID: ");
        final String owneruuid=in.nextLine();
        System.out.print("Owner Username: ");
        final String ownername=in.nextLine();
        System.out.println("\nCreating initial configuration file ...");

        m.put("firstname",firstname);
        m.put("lastname",lastname);
        m.put("CnC.authoriser","OwnerOnly");
        m.put("handlers","CnC,Sink,Health,Regions,Teleportation,Agent,Objects,Groups,Inventory");
        m.put("loginlocation","home");
        m.put("CnC.authorisation.owneruuid",owneruuid);
        m.put("CnC.authorisation.ownerusername",ownername);
        m.put("password",BotUtils.md5hash(password));

        try (final FileOutputStream fos = new FileOutputStream(CONFIGFILE); final ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(m);
        } catch (final FileNotFoundException ex) {
            throw new AssertionError("File not found creating file (incorrect directory??");
        } catch (final IOException ex) {
            throw new AssertionError("Unable to write configuration file",ex);
        }
        
        System.out.println("Configuration file created, initiating bot startup\n\n");
    }

}
