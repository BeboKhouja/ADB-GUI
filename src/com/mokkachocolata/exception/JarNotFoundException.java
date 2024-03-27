

package com.mokkachocolata.exception;

/**
 * Thrown when a .jar file is not found.
 * @since 1.2
 * @author Bebo Khouja
 */
public class JarNotFoundException extends Exception {
    /**
     * Constructs a {@code JarNotFoundException}.
     * @param message
     *        The message.
     * @since 1.2
     * @author Bebo Khouja
     */
    public JarNotFoundException(String message) {
        super(message);
    }
}
