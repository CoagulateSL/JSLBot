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

/** Handles the EventQueueGet CAPS.
 *
 * @author Iain Price
 */
public class EventQueue extends Thread {
    private final String eventqueue;
    private final CAPS caps;
    /** Create an event queue for the given CAPS, queue URL and region handle */
    EventQueue(CAPS caps, String queue) { this.caps=caps; eventqueue=queue; setDaemon(true); }
    /** Get the owning CAPS
     * @return  The CAPS object that owns this event queue
     */
    public CAPS caps() { return caps; }
    /** Get the owning circuit
     * @return  The circuit from the CAPS that owns this Event Queue
     */
    public Circuit circuit() { return caps().circuit(); }
    /** Get the owning bot
     * @return  Bot from the CAPS from circuit that owns this Event Queue
     */
    public JSLBot bot() { return circuit().bot(); }
    
    /** Call via start() to launch a background thread for polling the event queue */
    @Override
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
        // Event queue - poll the URL endlessly, most of the time it hangs for 30 seconds and '502's 
        // Otherwise it 200s and gives us a document.  Yay.
        // Either way we just keep doing this.  If we get a 404 then the URL has been invalidated and we can exit.
        int id=0;
        URL url=new URL(eventqueue);
        while (1==1) { // we could stop on circuit exit or some other things, but it seems to work fine just waiting for the inevitable 404
            // format request document
            LLSDMap post=new LLSDMap();
            post.put("ack",new LLSDInteger(id));
            post.put("done",new LLSDBoolean(false));
            LLSD postdoc=new LLSD(post);
            byte[] postdata=(postdoc.toXML().getBytes(StandardCharsets.UTF_8));
            // send it
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type","application/llsd+xml");
            connection.setRequestProperty("charset","utf-8");
            connection.setRequestProperty("Content-Length",Integer.toString(postdata.length));
            connection.setUseCaches(false);
            // write document
            try (DataOutputStream wr = new DataOutputStream( connection.getOutputStream())) {
                wr.write( postdata );
            }
            catch (Exception e) { warn("Error writing to event queue, sleeping"); try { Thread.sleep(5000); } catch (InterruptedException ee){}}
            if (Debug.EVENTQUEUE) { debug("Entering event queue wait"); }
            int status=connection.getResponseCode();
            if (status==404) { 
                info("EventQueue closed remotely");
                return;
            }
            if (status!=502) {
                String read="";
                try (Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                    for (int c; (c = in.read()) >= 0;)
                        read=read+((char)c);
                }
                //System.out.println("Event queue:"+read);
                LLSD document=null;
                try {
                    document=new LLSD(read);
                }
                catch (RuntimeException e) { error("Parse error loading LLSD document:"+e.toString()); System.out.println(read); }
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
            event.submit();
        }
    }
    public String getRegionName() { return caps().circuit().getRegionName(); }
    @Override
    public String toString() { return caps().toString()+" / EventQueue"; }
    private void debug(String message) { debug(message,null); }
    private void debug(String message, Throwable t) { Log.debug(this,message,t); }
    private void info(String message) { info(message,null); }
    private void info(String message, Throwable t) { Log.info(this,message,t); }
    private void note(String message) { note(message,null); }
    private void note(String message, Throwable t) { Log.note(this,message,t); }
    private void warn(String message) { warn(message,null); }
    private void warn(String message, Throwable t) { Log.warn(this,message,t); }
    private void error(String message) { error(message,null); }
    private void error(String message, Throwable t) { Log.error(this,message,t); }
    private void crit(String message) { crit(message,null); }
    private void crit(String message, Throwable t) { Log.crit(this,message,t); }


}
