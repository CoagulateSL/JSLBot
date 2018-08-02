package net.coagulate.JSLBot;

import java.io.IOException;
import java.util.Map;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

/**
 *
 * @author iain
 */
public class CommandEvent extends Event {
    // K=V style parameters, typeless
    private Map<String,String> parameters;
    public Map<String,String> parameters() {return parameters;}
    
    private LLUUID respondto; public LLUUID respondTo() { return respondto; }
    private String response=null;
    public void respondTo(String response) { this.response=response; }
    public String response() { return response; }

    boolean immediate=false;
    void immediate(boolean immediate) { this.immediate=immediate; }
    boolean immediate() { return immediate; }
    
    public CommandEvent(JSLBot bot,Regional r,String name,Map<String,String> parameters,LLUUID respondto) {
        super(bot, r,name);
        this.parameters=parameters;
        this.respondto=respondto;
    }

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
     * @throws IOException 
     */
    public void submit() throws IOException {
        immediate(false);
        bot().submit(this);
    }    
    /** Submits the command for immediate execution, the thread calling this function will be hijacked to execute the command.
     * There are sometimes very good reasons for this, and sometimes its definitely the wrong thing to do.  If you're not sure, use submit().
     * @return
     * @throws IOException 
     */
    public String execute() throws IOException {
        immediate(true);
        return bot().submit(this);
    }
        
}
