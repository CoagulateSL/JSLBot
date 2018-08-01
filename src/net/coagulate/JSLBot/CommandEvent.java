package net.coagulate.JSLBot;

import java.io.IOException;
import java.util.Map;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

/**
 *
 * @author iain
 */
public class CommandEvent extends Event {
    private Map<String,String> parameters;
    public Map<String,String> parameters() {return parameters;}
    private String name;
    @Override
    public String getName() {return name;}
    
    private LLUUID respondto; public LLUUID respondTo() { return respondto; }
    private String response=null;
    public void setResponse(String response) { this.response=response; }
    public String response() { return response; }

    boolean immediate=false;
    void immediate(boolean immediate) { this.immediate=immediate; }
    boolean immediate() { return immediate; }
    
    public CommandEvent(JSLBot bot,Regional r,String name,Map<String,String> parameters,LLUUID respondto) {
        super(bot, r);
        this.name=name;
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

    public void submit() throws IOException {
        immediate(false);
        bot().submit(this);
    }    
    public String execute() throws IOException {
        immediate(true);
        return bot().submit(this);
    }
        
}
