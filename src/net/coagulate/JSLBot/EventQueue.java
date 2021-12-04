package net.coagulate.JSLBot;

import net.coagulate.Core.Tools.ByteTools;
import net.coagulate.JSLBot.LLSD.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

/**
 * Handles the EventQueueGet CAPS.
 *
 * @author Iain Price
 */
public class EventQueue extends Thread {
	private final Logger log;
	private final String eventqueue;
	@Nonnull
	private final CAPS caps;

	/**
	 * Create an event queue for the given CAPS, queue URL and region handle
	 */
	EventQueue(@Nonnull final CAPS caps,
	           final String queue) {
		log=caps.getLogger("EventQueue");
		this.caps=caps;
		eventqueue=queue;
		setDaemon(true);
	}

	// ---------- INSTANCE ----------

	/**
	 * Get the owning CAPS
	 *
	 * @return The CAPS object that owns this event queue
	 */
	@Nonnull
	public CAPS caps() { return caps; }

	/**
	 * Get the owning circuit
	 *
	 * @return The circuit from the CAPS that owns this Event Queue
	 */
	@Nonnull
	public Circuit circuit() { return caps().circuit(); }

	/**
	 * Get the owning bot
	 *
	 * @return Bot from the CAPS from circuit that owns this Event Queue
	 */
	@Nonnull
	public JSLBot bot() { return circuit().bot(); }
	@Nullable
	public JSLBot botNullable() { return circuit().botNullable(); }
	/**
	 * Call via start() to launch a background thread for polling the event queue
	 */
	@Override
	public void run() {
		setName("Event queue driver for "+bot().getUsername()+" to "+circuit().getRegionName());
		try {
			runMain();
			log.fine("Event queue closed");
		}
		catch (@Nonnull final Exception e) {
			log.log(SEVERE,"Event queue crashed: "+e,e);
		}
		@Nullable JSLBot mybot=botNullable();
		if (mybot==null) {
			log.log(SEVERE,"CRITICAL (consequential) FAILURE - primary caps is closed, bot has already disconnected from us");
			return;
		}
		if (mybot.circuit().getCAPS().eventqueue()==this && mybot.connected()) {
			log.log(SEVERE,"CRITICAL FAILURE - primary caps circuit is closed, this is reason to reconnect");
			mybot.shutdown("Primary event queue CAPS failed.");
		}
	}

	@Nonnull
	@Override
	public String toString() { return caps()+" / EventQueue"; }

	@Nonnull
	public String getRegionName() { return caps().circuit().getRegionName(); }

	private boolean shutdown=false;
	public void shutdown() { shutdown=true; }
	// ----- Internal Instance -----
	@SuppressWarnings("BusyWait")
	private void runMain() throws Exception {
		// Event queue - poll the URL endlessly, most of the time it hangs for 30 seconds and '502's
		// Otherwise it 200s and gives us a document.  Yay.
		// Either way we just keep doing this.  If we get a 404 then the URL has been invalidated and we can exit.
		int id=0;
		@Nonnull final URL url=new URL(eventqueue);
		int errorcount=0;
		while (!shutdown) { // we could stop on circuit exit or some other things, but it seems to work fine just waiting for the inevitable 404
			try {
				// format request document
				@Nonnull final LLSDMap post=new LLSDMap();
				post.put("ack",new LLSDInteger(id));
				post.put("done",new LLSDBoolean(false));
				@Nonnull final LLSD postdoc=new LLSD(post);
				@Nonnull final byte[] postdata=(postdoc.toXML().getBytes(StandardCharsets.UTF_8));
				// send it
				@Nonnull final HttpURLConnection connection=(HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type","application/llsd+xml");
				connection.setRequestProperty("charset","utf-8");
				connection.setRequestProperty("Content-Length",Integer.toString(postdata.length));
				connection.setUseCaches(false);
				// write document
				try (@Nonnull final DataOutputStream wr=new DataOutputStream(connection.getOutputStream())) {
					wr.write(postdata);
				}
				catch (@Nonnull final Exception e) {
					log.warning("Error writing to event queue, sleeping");
					try { Thread.sleep(5000); } catch (@Nonnull final InterruptedException ignored) {}
				}
				if (Debug.EVENTQUEUE) { log.finer("Entering event queue wait"); }
				final int status=connection.getResponseCode();
				if (status==404) {
					log.info("EventQueue closed remotely");
					return;
				}
				if (status==500) {
					log.warning("500 Error: "+ByteTools.convertStreamToString(connection.getErrorStream()));
					return;
				}
				if (status!=502 && status!=499 && status!=500) {
					@Nonnull final Scanner s=new Scanner(connection.getInputStream()).useDelimiter("\\A");
					final String read=s.next();
					//System.out.println("Event queue:"+read);
					@Nullable LLSD document=null;
					try {
						document=new LLSD(read);
					}
					catch (@Nonnull final RuntimeException e) {
						log.log(SEVERE,"Parse error loading LLSD document:"+e);
						System.out.println(read);
					}
					if (document!=null) {
						try {
							@Nonnull final LLSDMap map=(LLSDMap) document.getFirst();
							if (map==null) {
								log.log(SEVERE,"Loaded null LLSDMap from document "+read+", throwing IOException");
								throw new IOException("Null LLSDMap response extracted from '"+read+"'");
							}
							else {
								@Nonnull final LLSDInteger llsdid=(LLSDInteger) map.get("id");
								id=llsdid.get();
								//System.out.println("Eventqueue#"+id+":"+document.toXML());
								@Nonnull final LLSDMap outermap=(LLSDMap) document.getFirst();
								@Nonnull final LLSDArray eventslist=(LLSDArray) outermap.get("events");
								process(eventslist);
							}
						}
						catch (@Nonnull final Exception e) {
							log.log(SEVERE,"Exception processing event queue message",e);
							throw new IOException(e);
						}
					}
				}
				else { if (Debug.EVENTQUEUE) { log.finer("Event queue poller expired, repolling."); } }
				errorcount=0;
			}
			catch (@Nonnull final IOException e) {
				errorcount++;
				if (errorcount>10) {
					log.log(SEVERE,"10 errors in a row polling event queue, closing event queue",e);
					throw new IOException("Too many event queue IOExceptions occured, terminating EventQueue",e);
				}
				log.fine("IOException during Event Queue poll, errorcount is "+errorcount+" / 10 : "+e.getLocalizedMessage());
			}
		}
		log.fine("Event queue was shut down");
	}

	private void process(@Nonnull final LLSDArray events) {
		for (final Atomic a: events.get()) {
			//System.out.println("**************** ATOM:\n"+a.toXML());
			// this is so clunky
			// should be a map, "message" key and a "body" key, with which we can commence the decode
			@Nonnull final LLSDMap eventmap=(LLSDMap) a;
			final String messagetype=eventmap.get("message").toString();
			final Atomic body=eventmap.get("body");
			if (Debug.DUMPXML) { System.out.println("Message type is "+messagetype+"\n"+body.toXML()); }
			@Nonnull final XMLEvent event=new XMLEvent(bot(),circuit().regional(),body,messagetype);
			event.submit();
		}
	}

}
