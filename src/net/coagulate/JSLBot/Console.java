package net.coagulate.JSLBot;

import net.coagulate.JSLBot.Handlers.CnC;

import java.util.Scanner;

/**
 * Runs a console input/output stream for running commands
 */

public class Console {

    static boolean exit;

    public static void run(final JSLBot bot) {
        final Scanner scanner = new Scanner(System.in);
        while (!exit) {
            try {
                execute(bot, scanner.nextLine());
            } catch (final Exception e) {
                System.err.println("========== CONSOLE COMMAND GENERATED EXCEPTION =====");
                e.printStackTrace();
                System.err.println("========== CONSOLE COMMAND GENERATED EXCEPTION =====");
            }
        }
    }

    private static void execute(final JSLBot bot, final String line) throws Exception {
        ((CnC) (bot.brain().getHandler("CnC"))).
                runCommands("Local Console Input", null, line, null, true);
    }
}
