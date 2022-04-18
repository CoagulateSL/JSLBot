package net.coagulate.JSLBot;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static net.coagulate.JSLBot.Event.EVENTTYPE.*;
import static net.coagulate.JSLBot.Event.STATUS.COMPLETE;
import static net.coagulate.JSLBot.Event.STATUS.UNSUBMITTED;


/**
 * Abstract superclass of different types of event we propagate.
 *
 * @author Iain Price
 */
public abstract class Event {

	// locking the status field, command event monitors this
	final Object statusmonitor=new Object();
	@Nonnull
	private final Regional r;
	@Nonnull
	private final JSLBot bot;
	@Nonnull
	private final String name;
	private Logger log;
	private STATUS status=UNSUBMITTED;
	private EVENTTYPE type;

	Event(@Nonnull final JSLBot bot,
	      @Nonnull final Regional r,
	      @Nonnull final String name) {
		this.bot=bot;
		this.r=r;
		this.name=name;
		setType();
	}

	// ---------- INSTANCE ----------

	/**
	 * Get the event's current status
	 *
	 * @return The STATUS of this event
	 */
	public STATUS status() { return status; }

	/**
	 * Get the events type
	 *
	 * @return The EVENTTYPE of this event
	 */
	public EVENTTYPE type() { return type; }

	/**
	 * Returns the type as a string
	 *
	 * @return The type of this event, UDP, XML or Command, as a string
	 */
	@Nonnull
	public String typeString() {
		if (type==UDP) { return "UDP"; }
		if (type==XML) { return "XML"; }
		if (type==COMMAND) { return "Command"; }
		throw new AssertionError("Unknown type.  How did you get here?  The constructor should have thrown this exception.");
	}

	/**
	 * Region this event originated from, if applicable
	 *
	 * @return Regional data for the originating region
	 */
	@Nonnull
	public Regional region() {
		return r;
	}

	@Nonnull
	public JSLBot bot() {
		return bot;
	}

	@Nonnull
	@Override
	public String toString() {
		return region()+"/"+getPrefixedName();
	}

	@Nonnull
	public final String getName() { return name; }

	public abstract String dump();

	@Nullable
	public String getPrefixedName() {
		return typeString()+"/"+getName();
	}

	/**
	 * Submit event for full queue processing.
	 * While commands choose IMMEDIATE/DELAYED mode, every other event passes through both and the handlers decide where to be installed.
	 */
	public void submit() {
		bot().brain().execute(this);
	}

	public void waitFinish(final long milliseconds) {
		final long expire=new Date().getTime()+milliseconds;
		while ((new Date().getTime())<expire) {
			synchronized (statusmonitor) {
				try { statusmonitor.wait(1000); } catch (@Nonnull final InterruptedException ignored) { }
			}
			if (status==COMPLETE) { return; }
		}
		throw new IllegalStateException("Command timed out in status "+status());
	}

	// ----- Internal Instance -----
	void log(final Level level,
	         final String message) {
		log.log(level,message);
	}

	/**
	 * Set the event's current status
	 *
	 * @param status the new status for this event
	 */
	void status(final STATUS status) {
		synchronized (statusmonitor) {
			this.status=status;
			if (Debug.TRACKCOMMANDS && type==COMMAND) {
				log.log(Level.FINER,"Command {0} in region {1}entering status {2}",new Object[]{getName(),region().toString(),status});
			}
			statusmonitor.notifyAll();
		}
	}

	private void setType() {
		if (this instanceof UDPEvent) {
			type=UDP;
			return;
		}
		if (this instanceof XMLEvent) {
			type=XML;
			return;
		}
		if (this instanceof CommandEvent) {
			type=COMMAND;
			return;
		}
		throw new IllegalArgumentException("This class "+getClass().getName()+" is not assignable from the expected types");
	}

	/**
	 * Stages an event goes through
	 */
	public enum STATUS {
		/**
		 * Freshly created unprocessed event.
		 */
		UNSUBMITTED,
		/**
		 * Event running through the immediate handlers
		 */
		IMMEDIATE,
		/**
		 * Event queued for delayed handlers
		 */
		QUEUED,
		/**
		 * Event running in delayed handlers
		 */
		RUNNING,
		/**
		 * Event completed
		 */
		COMPLETE
	}

	/**
	 * Supported event types
	 */
	public enum EVENTTYPE {
		UDP,
		XML,
		COMMAND
	}
}
