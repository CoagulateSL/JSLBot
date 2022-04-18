package net.coagulate.JSLBot;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Stand alone single bot launcher.
 *
 * @author Iain Price
 */
public class Test {

	private static boolean CONSOLE;
	// ---------- STATICS ----------

	/**
	 * Launch a singular bot using a config store and lose our thread to its AI thread.
	 *
	 * @param args the command line arguments
	 */
	public static void main(@Nonnull final String[] args) {
		System.out.println("JSLBot single-instance wrapper starting up (now supports --console)");
		// as far as multiple bots go you have two options if you want to use this config system
		// 1) You can just "sub-space" the configuration namespace; make a new Config(Config,prefix) and write your login values as "prefix.firstname" etc
		// then pass each bot the sub-space of the configuration
		// 2) alternatively you can just make a configuration for each bot from scratch, each in its own store or file.
		// a lot depends on your backing store, the file provider can serve different files to different bots via instansiation,
		// or separate namespaces of one file to separate bots, via recursive instansiation (see Configuration's constructors)
		final String CONFIGFILE;
		if (args.length>0) { CONFIGFILE=args[0]; }
		else {
			@Nonnull final Scanner in=new Scanner(System.in);
			System.out.print("Name of config file: ");
			CONFIGFILE=in.nextLine();
		}
		for (final String argument : args) {
            if ("--console".equalsIgnoreCase(argument)) {
				CONSOLE = true;
				System.out.println("Enabling console command mode");
			}
        }
		if (CONFIGFILE==null) { throw new NullPointerException("You must supply a configuration file name"); }
		if (CONFIGFILE.isBlank()) { throw new AssertionError("You must supply a file name so that we can create a configuration file!"); }
		if (!(new File(CONFIGFILE).exists())) {initConfig(CONFIGFILE);}
		@Nonnull final Configuration config=new FileBasedConfiguration(CONFIGFILE);
		//System.out.println("===== Configuration file loaded =====\n"+config.dump());
		@Nonnull final JSLBot bot=new JSLBot(config);
		bot.ALWAYS_RECONNECT=true; // likely this will be cleaned up, but for testing...
		if (!CONSOLE) {
			//noinspection CallToThreadRun
			bot.run(); // lose control to bot.  call start() to background the bot and continue execution here.
			return;
		}
		// console mode
		bot.start();
		Console.run(bot);
	}

	// ----- Internal Statics -----

	/**
	 * Builds a base configuration file
	 */
	static void initConfig(@Nonnull final String CONFIGFILE) {
		@Nonnull final Map<String,String> m=new HashMap<>();

		System.out.println("---- ALERT ----\nConfiguration file '"+CONFIGFILE+"' does not exist.\nIf you complete this process it will be created\n\n");
		@Nonnull final Scanner in=new Scanner(System.in);

		System.out.print("Bot's first name: ");
		final String firstname=in.nextLine();
		System.out.print("Bot's last name: ");
		final String lastname=in.nextLine();
		System.out.print("Bot's password: ");
		final String password=in.nextLine();
		System.out.println("Handlers: CnC,Sink,Health,Regions,Teleportation,Agent,Objects,Groups,Inventory");
		System.out.println("Login Location: home");
		System.out.println("Authoriser: OwnerOnly");
		System.out.print("Owner UUID: ");
		final String owneruuid=in.nextLine();
		System.out.print("Owner Username: ");
		final String ownername=in.nextLine();
		System.out.print("Login URI (leave blank for Second Life default): ");
		final String loginuri=in.nextLine();
		System.out.println("\nCreating initial configuration file ...");

		m.put("firstname",firstname);
		m.put("lastname",lastname);
		m.put("CnC.authoriser","OwnerOnly");
		m.put("handlers","CnC,Sink,Health,Regions,Teleportation,Agent,Objects,Groups,Inventory");
		m.put("loginlocation","home");
		m.put("CnC.authorisation.owneruuid",owneruuid);
		m.put("CnC.authorisation.ownerusername",ownername);
		m.put("password",BotUtils.md5hash(password));
		m.put("loginuri",loginuri);
		try (@Nonnull final FileOutputStream fos=new FileOutputStream(CONFIGFILE); @Nonnull final ObjectOutputStream oos=new ObjectOutputStream(fos)) {
			oos.writeObject(m);
		}
		catch (@Nonnull final FileNotFoundException ex) {
			throw new AssertionError("File not found creating file (incorrect directory??", ex);
		}
		catch (@Nonnull final IOException ex) {
			throw new AssertionError("Unable to write configuration file",ex);
		}

		System.out.println("Configuration file created, initiating bot startup\n\n");
	}

}
