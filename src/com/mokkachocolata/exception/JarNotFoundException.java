

package com.mokkachocolata.exception;

/**
 * Thrown when the Jar is not found.
 * @since 1.2
 */
public class JarNotFoundException extends Exception {
    /**
     * Constructs a {@code JarNotFoundException}.
     * @param message
     *        The message. Can also be {@code null}.
     */
    public JarNotFoundException(String message) {
        super(message);
    }
}
