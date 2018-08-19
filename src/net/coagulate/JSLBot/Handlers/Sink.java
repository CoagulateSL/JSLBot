package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.UDPEvent;
import net.coagulate.JSLBot.XMLEvent;

/**  Used to absorb message types we're not interested in and dont want unhandled warnings about.
 * Mostly audio/visual cues.
 * @author Iain Price
 */
public class Sink extends Handler {
    public Sink(JSLBot bot,Configuration c) {super(bot,c);}
    
    public void simStatsUDPDelayed(UDPEvent event){}
    public void avatarAppearanceUDPDelayed(UDPEvent event){}
    public void attachedSoundUDPDelayed(UDPEvent event){}
    public void viewerEffectUDPDelayed(UDPEvent event){}
    public void attachedSoundGainChangeUDPDelayed(UDPEvent event){}
    public void regionHandshakeUDPDelayed(UDPEvent event){}
    public void soundTriggerUDPDelayed(UDPEvent event){}
    public void preloadSoundUDPDelayed(UDPEvent event){}
    public void layerDataUDPDelayed(UDPEvent event){}
    public void cameraConstraintUDPDelayed(UDPEvent event){}
    public void avatarAnimationUDPDelayed(UDPEvent event){}
    public void agentStateUpdateUDPDelayed(UDPEvent event){}
    public void agentStateUpdateXMLDelayed(XMLEvent event){}

    @Override
    public void loggedIn() throws Exception {
    }
}
