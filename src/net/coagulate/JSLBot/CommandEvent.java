package net.coagulate.JSLBot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

/** An event that represents an invoked command.
 * Note there are two execution types, submit (delayed mode) and execute (immediate mode).
 * As always, know what you're doing with immediate mode execution.
 *
 * @author Iain Price
 */
public class CommandEvent extends Event {
    // K=V style parameters, typeless
    private final Map<String,String> parameters;
    public Map<String,String> parameters() {return parameters;}
    // auto IM the response to
    private final LLUUID respondto; public LLUUID respondTo() { return respondto; }
    public void respondTo(String response) { this.response=response; }
    // the response its self, for other threads to read.
    private String response=null;
    public String response() { return response; }
    void response(String response) { this.response=response; }


    private boolean immediate=false;
    private void immediate(boolean immediate) { this.immediate=immediate; }
    boolean immediate() { return immediate; }
    
    public CommandEvent(JSLBot bot,Regional r,String name,Map<String,String> parameters,LLUUID respondto) {
        super(bot, r,name.toLowerCase());
        this.parameters=parameters;
        this.respondto=respondto;
    }

    // for evaluating the 'authorisation' of this command
    private String invokerusername=null;
    public String invokerUsername() { return invokerusername; }
    public void invokerUsername(String invoker) { this.invokerusername=invoker; }
    private LLUUID invokeruuid=null;
    public LLUUID invokerUUID() { return invokeruuid; }
    public void invokerUUID(LLUUID invokeruuid) { this.invokeruuid=invokeruuid; }
    
    @Override
    public String dump() {
        String ret="COMMAND: "+getName();
        for (String k:parameters.keySet()) {
            ret=ret+"\n"+k+"="+parameters.get(k);
        }
        return ret;
    }

    /** Submits this command onto the queue for DELAYED processing by the AI thread.
     * This is the most normal way to do things unless you know better. 
     */
    @Override
    public void submit() {
        immediate(false);
        bot().brain().queue(this);
    }    
    /** Submits the command for DELAYED processing, then halts this thread until it completes or times out
     * 
     * @param timeoutmillis Number of milliseconds before giving up waiting
     * @return Command response
     */
    public String submitAndWait(long timeoutmillis) {
        submit();
        waitFinish(timeoutmillis);
        return response();
    }
    /** Submits the command for Delayed processing and waits 10 seconds for it to complete
     * @return The final response from the command.
     */
    public String submitAndWait() { return submitAndWait(10000); }
    /** Submits the command for immediate execution, the thread calling this function will be hijacked to execute the command.
     * There are sometimes very good reasons for this, and sometimes its definitely the wrong thing to do.  If you're not sure, use submit().
     * @return Command response
     */
    public String execute() {
        immediate(true);
        return bot().brain().execute(this);
    }

    String run(Object callon, Method handler) {
        if(Debug.TRACKCOMMANDS) { Log.debug(this,"Entering run() for "+handler.getName()+" in class "+callon.getClass().getSimpleName()); }
        try {
            return (String) handler.invoke(callon,getParameters(handler).toArray());
        } catch (IllegalAccessException|IllegalArgumentException ex) {
            throw new AssertionError("Error accessing "+handler.getName()+" in class "+callon.getClass().getName(),ex);
        } catch (InvocationTargetException ex) {
            Throwable t=ex;
            if (t.getCause()!=null) { t=t.getCause(); }
            Log.debug(this,"Handler "+handler.getName()+" in class "+callon.getClass().getSimpleName()+" threw exception "+t.toString(),t);
            return "Exception:"+t.toString();
        }
    }
    

    private List<Object> getParameters(Method method) {
        List<Object> params=new ArrayList<>();
        params.add(this); boolean firstparam=true;
        for (Parameter param:method.getParameters()) {
            //System.out.println(param.toString());
            if (firstparam) { firstparam=false; }
            else {
                String paramname=param.getName();
                if (parameters().containsKey(paramname)) { params.add(parameters().get(paramname)); }
                else { params.add(null); }
            }
        }
        return params;
    }    

}
