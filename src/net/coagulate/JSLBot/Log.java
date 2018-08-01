package net.coagulate.JSLBot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Simple logging thing.
 *
 * @author Iain Price <git@predestined.net>
 */
public class Log {
    public static final int DEBUG=-1;
    public static final int INFO=0;
    public static final int NOTE=1;
    public static final int WARN=2;
    public static final int WARNING=WARN;
    public static final int ERR=3;
    public static final int ERROR=ERR;
    public static final int CRIT=4;
    public static final int CRITICAL=CRIT;
    public static final int ABORT=CRIT;
    public static final int CRASH=CRIT; // why not
    public static final DateFormat datetime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    
    public static String decode(int l) {
        switch (l) {
            case -1: return "debug";
            case 0: return "Info"; 
            case 1: return "Note"; 
            case 2: return "Warning";
            case 3: return "ERROR";
            case 4: return "CRITICAL"; 
            default: return "Unk?"+l;
        }
    }
    
    public static String decodeFixed(int l) {
        switch (l) {
            case -1: return "-dbg";
            case 0: return "info";
            case 1: return "note";
            case 2: return "Warn";
            case 3: return "Err!";
            case 4: return "CRIT";
            default: return "?"+l+"?!";
        }
    }
    public static void log(JSLBot bot,int errorlevel,String message) { log(bot,errorlevel,message,null); }
    public static void log(JSLBot bot,int errorlevel,String message,Throwable t) {
        String username="SYSTEM";
        if (bot!=null) { username=bot.getUsername(); }
        String tds=datetime.format(new Date());
        System.out.println("["+decodeFixed(errorlevel)+"] "+tds+" <"+username+"> - "+message);
        if (t!=null) { t.printStackTrace(System.out); }
    }
    
    public static void debug(JSLBot bot,String message) { debug(bot,message,null); }
    public static void debug(JSLBot bot,String message,Throwable t) { log(bot,DEBUG,message,t); }
    public static void info(JSLBot bot,String message) { info(bot,message,null); }
    public static void info(JSLBot bot,String message,Throwable t) { log(bot,INFO,message,t); }
    public static void note(JSLBot bot,String message) { note(bot,message,null); }
    public static void note(JSLBot bot,String message,Throwable t) { log(bot,NOTE,message,t); }
    public static void warn(JSLBot bot,String message) { warn(bot,message,null); }
    public static void warn(JSLBot bot,String message,Throwable t) { log(bot,WARNING,message,t); }
    public static void error(JSLBot bot,String message) { error(bot,message,null); }
    public static void error(JSLBot bot,String message,Throwable t) { log(bot,ERROR,message,t); }
    public static void crit(JSLBot bot,String message) { crit(bot,message,null); }
    public static void crit(JSLBot bot,String message,Throwable t) { log(bot,CRITICAL,message,t); }
            
}
