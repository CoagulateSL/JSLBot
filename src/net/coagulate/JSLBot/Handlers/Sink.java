package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.*;

import javax.annotation.Nonnull;

/**
 * Used to absorb message types we're not interested in and dont want unhandled warnings about.
 * Mostly audio/visual cues.
 *
 * @author Iain Price
 */
public class Sink extends Handler {
	public Sink(@Nonnull final JSLBot bot,
	            final Configuration c) {super(bot,c);}

	// ---------- INSTANCE ----------
	public void simStatsUDPDelayed(final UDPEvent event) {}

	public void avatarAppearanceUDPDelayed(final UDPEvent event) {}

	public void attachedSoundUDPDelayed(final UDPEvent event) {}

	public void viewerEffectUDPDelayed(final UDPEvent event) {}

	public void attachedSoundGainChangeUDPDelayed(final UDPEvent event) {}

	public void regionHandshakeUDPDelayed(final UDPEvent event) {}

	public void soundTriggerUDPDelayed(final UDPEvent event) {}

	public void preloadSoundUDPDelayed(final UDPEvent event) {}

	public void layerDataUDPDelayed(final UDPEvent event) {}

	public void cameraConstraintUDPDelayed(final UDPEvent event) {}

	public void avatarAnimationUDPDelayed(final UDPEvent event) {}

	public void agentStateUpdateUDPDelayed(final UDPEvent event) {}

	public void agentStateUpdateXMLDelayed(final XMLEvent event) {}

}
