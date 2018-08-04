/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coagulate.JSLBot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import net.coagulate.JSLBot.LLSD.Atomic;
import net.coagulate.JSLBot.LLSD.LLSD;
import net.coagulate.JSLBot.LLSD.LLSDArray;
import net.coagulate.JSLBot.LLSD.LLSDBoolean;
import net.coagulate.JSLBot.LLSD.LLSDInteger;
import net.coagulate.JSLBot.LLSD.LLSDMap;
import net.coagulate.JSLBot.LLSD.LLSDString;

/**
 *
 * @author Iain
 */
public class EventQueue extends Thread {
    private String eventqueue;
    private CAPS caps;
    private long regionhandle;
    public EventQueue(CAPS caps,String queue,long regionhandle) { this.caps=caps; eventqueue=queue;  this.regionhandle=regionhandle; setDaemon(true); }
    /** Get the owning CAPS */
    public CAPS caps() { return caps; }
    /** Get the owning circuit */
    public Circuit circuit() { return caps().circuit(); }
    /** Get the owning bot */
    public JSLBot bot() { return circuit().bot(); }
    
    /** Call via start() to launch a background thread for polling the event queue */
    public void run() {
        setName("Event queue driver for "+bot().getUsername()+" to "+circuit().getRegionName());
        try {
            runMain();
        }
        catch (Exception e) {
            error("Event queue crashed: "+e.toString(),e);
        }
    }
    
    private void runMain() throws Exception {
        int id=0;
        URL url=new URL(eventqueue);
        while (1==1) {
            
            LLSDMap post=new LLSDMap();
            post.put("ack",new LLSDInteger(id));
            post.put("done",new LLSDBoolean(false));
            LLSD postdoc=new LLSD(post);
            byte[] postdata=(postdoc.toXML().getBytes(StandardCharsets.UTF_8));
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type","application/llsd+xml");
            connection.setRequestProperty("charset","utf-8");
            connection.setRequestProperty("Content-Length",Integer.toString(postdata.length));
            connection.setUseCaches(false);

            try( DataOutputStream wr = new DataOutputStream( connection.getOutputStream())) {
                  wr.write( postdata );
            }
            if (Debug.EVENTQUEUE) { debug("Entering event queue wait"); }
            int status=connection.getResponseCode();
            if (status==404) { 
                info("EventQueue closed remotely");
                return;
            }
            if (status!=502) {
                Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String read="";
                for (int c; (c = in.read()) >= 0;)
                    read=read+((char)c);
                //System.out.println("Event queue:"+read);
                LLSD document=null;
                try {
                    document=new LLSD(read);
                }
                catch (Exception e) { error("Parse error loading LLSD document:"+e.toString()); System.out.println(read); }
                if (document!=null) {
                    try {
                        LLSDMap map=(LLSDMap) document.getFirst();
                        LLSDInteger llsdid=(LLSDInteger) map.get("id");
                        id=llsdid.get();
                        //System.out.println("Eventqueue#"+id+":"+document.toXML());
                        LLSDMap outermap=(LLSDMap) document.getFirst();
                        LLSDArray eventslist = (LLSDArray) outermap.get("events");
                        process(eventslist);
                    }
                    catch (Exception e) { error("Exception processing event queue message",e); }
                }
            }
            else { if (Debug.EVENTQUEUE) { debug("Event queue poller expired, repolling."); } }
        }
    }
    
    private void process(LLSDArray events) throws Exception {
        for (Atomic a:events.get()) {
            //System.out.println("**************** ATOM:\n"+a.toXML());
            // this is so clunky
            // should be a map, "message" key and a "body" key, with which we can commence the decode
            LLSDMap eventmap=(LLSDMap) a;
            String messagetype=((LLSDString)eventmap.get("message")).toString();
            Atomic body=eventmap.get("body");
            if (Debug.DUMPXML) { System.out.println("Message type is "+messagetype+"\n"+body.toXML()); }
            XMLEvent event=new XMLEvent(bot(), circuit().regional(), body, messagetype);
            bot().submit(event);
        }
    }
    public String getRegionName() { return caps().circuit().getRegionName(); }
    void debug(String message) { debug(message,null); }
    void debug(String message, Throwable t) { Log.log(bot(),Log.DEBUG,"("+getRegionName()+") "+message,t); }
    void info(String message) { info(message,null); }
    void info(String message, Throwable t) { Log.log(bot(),Log.INFO,"("+getRegionName()+") "+message,t); }
    void note(String message) { note(message,null); }
    void note(String message, Throwable t) { Log.log(bot(),Log.NOTE,"("+getRegionName()+") "+message,t); }
    void warn(String message) { warn(message,null); }
    void warn(String message, Throwable t) { Log.log(bot(),Log.WARNING,"("+getRegionName()+") "+message,t); }
    void error(String message) { error(message,null); }
    void error(String message, Throwable t) { Log.log(bot(),Log.ERROR,"("+getRegionName()+") "+message,t); }
    void crit(String message) { crit(message,null); }
    void crit(String message, Throwable t) { Log.log(bot(),Log.CRITICAL,"("+getRegionName()+") "+message,t); }    


}
