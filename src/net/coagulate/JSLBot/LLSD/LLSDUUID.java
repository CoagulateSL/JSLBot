package net.coagulate.JSLBot.LLSD;

import net.coagulate.JSLBot.Packets.Types.LLUUID;
import org.w3c.dom.Node;

import javax.annotation.Nonnull;

/** Represent a UUID in LLSD.
 * Backed by the LLUUID class.
 * @author Iain Price
 */
public class LLSDUUID extends Atomic {

    private final LLUUID value;

    /** Create a LSLUUID from a LLUUID
     * 
     * @see LLUUID
     * @param uuid the LLUUID
     */
    public LLSDUUID(final LLUUID uuid) { value =uuid; }
    
    /** Create a LSLUUID from a UUID string
     *
     * @see LLUUID
     * @param uuid UUID as a string
     */
    public LLSDUUID(final String uuid) {
        value=new LLUUID(uuid);
    }

    /** Create a LLSDUUID from a document node.
     * @see LLUUID
     * @param item Item to extract the UUID contents of
     */
    public LLSDUUID(@Nonnull final Node item) {
        value=new LLUUID(item.getTextContent());
    }

    /** Create a null (0's) UUID.
     * @see LLUUID
     */
    public LLSDUUID() {
        value=new LLUUID();
    }

    /** Write the XML UUID tag pair representing this atom.
     *
     * @param lineprefix Indent
     * @return XML Format
     */
    @Nonnull
    @Override
    public String toXML(final String lineprefix) {
        return lineprefix+"<uuid>"+value.toUUIDString()+"</uuid>\n";
    }
    @Override
    public String toString() { return value.toString(); }

    /** Get this UUID as a string.
     *
     * @return String of this UUID.
     */
    public String get() { return value.toString(); }

    /** Get this LLSDUUID as an LLUUID.
     * Returns the internal representation.
     * @see LLUUID
     * @return The LLUID for this UUID
     */
    public LLUUID toLLUUID() {
        return value;
    }
}
