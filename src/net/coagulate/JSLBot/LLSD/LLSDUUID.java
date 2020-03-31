package net.coagulate.JSLBot.LLSD;

import net.coagulate.JSLBot.Packets.Types.LLUUID;
import org.w3c.dom.Node;

import javax.annotation.Nonnull;

/**
 * Represent a UUID in LLSD.
 * Backed by the LLUUID class.
 *
 * @author Iain Price
 */
public class LLSDUUID extends Atomic {

	private final LLUUID value;

	/**
	 * Create a LSLUUID from a LLUUID
	 *
	 * @param uuid the LLUUID
	 *
	 * @see LLUUID
	 */
	public LLSDUUID(final LLUUID uuid) { value=uuid; }

	/**
	 * Create a LSLUUID from a UUID string
	 *
	 * @param uuid UUID as a string
	 *
	 * @see LLUUID
	 */
	public LLSDUUID(final String uuid) {
		value=new LLUUID(uuid);
	}

	/**
	 * Create a LLSDUUID from a document node.
	 *
	 * @param item Item to extract the UUID contents of
	 *
	 * @see LLUUID
	 */
	public LLSDUUID(@Nonnull final Node item) {
		value=new LLUUID(item.getTextContent());
	}

	/**
	 * Create a null (0's) UUID.
	 *
	 * @see LLUUID
	 */
	public LLSDUUID() {
		value=new LLUUID();
	}

	// ---------- INSTANCE ----------

	/**
	 * Write the XML UUID tag pair representing this atom.
	 *
	 * @param lineprefix Indent
	 *
	 * @return XML Format
	 */
	@Nonnull
	@Override
	public String toXML(final String lineprefix) {
		return lineprefix+"<uuid>"+value.toUUIDString()+"</uuid>\n";
	}

	@Override
	public String toString() { return value.toString(); }

	/**
	 * Get this UUID as a string.
	 *
	 * @return String of this UUID.
	 */
	public String get() { return value.toString(); }

	/**
	 * Get this LLSDUUID as an LLUUID.
	 * Returns the internal representation.
	 *
	 * @return The LLUID for this UUID
	 *
	 * @see LLUUID
	 */
	public LLUUID toLLUUID() {
		return value;
	}
}
