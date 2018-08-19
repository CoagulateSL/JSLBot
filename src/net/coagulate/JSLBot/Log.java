package net.coagulate.JSLBot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/** A logging system.
 * That should probably be replaced by something nowadays in the core APIs.
 * @author Iain Price
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
    public static boolean LOGTDS=true;
    
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
    
    public static void log(Object instance,int errorlevel,String message) { log(instance,errorlevel,message,null); }
    public static void log(Object instance,int errorlevel,String message,Throwable t) {
        String id="SYSTEM";
        if (instance!=null) { id=instance.toString(); }
        String tds="";
        if (LOGTDS) { tds=datetime.format(new Date())+" "; }
        System.out.println("["+decodeFixed(errorlevel)+"] "+tds+"<"+id+"> - "+message);
        if (t!=null) { t.printStackTrace(System.out); }
    }
    
    public static void debug(Object instance,String message) { debug(instance,message,null); }
    public static void debug(Object instance,String message,Throwable t) { log(instance,DEBUG,message,t); }
    public static void info(Object instance,String message) { info(instance,message,null); }
    public static void info(Object instance,String message,Throwable t) { log(instance,INFO,message,t); }
    public static void note(Object instance,String message) { note(instance,message,null); }
    public static void note(Object instance,String message,Throwable t) { log(instance,NOTE,message,t); }
    public static void warn(Object instance,String message) { warn(instance,message,null); }
    public static void warn(Object instance,String message,Throwable t) { log(instance,WARNING,message,t); }
    public static void error(Object instance,String message) { error(instance,message,null); }
    public static void error(Object instance,String message,Throwable t) { log(instance,ERROR,message,t); }
    public static void crit(Object instance,String message) { crit(instance,message,null); }
    public static void crit(Object instance,String message,Throwable t) { log(instance,CRITICAL,message,t); }
            
}
