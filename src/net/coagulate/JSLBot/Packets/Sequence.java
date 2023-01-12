package net.coagulate.JSLBot.Packets;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Defines an annotation for sequencing fields in a class.
 *
 * @author Iain Price
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Sequence {
	int value();
}
