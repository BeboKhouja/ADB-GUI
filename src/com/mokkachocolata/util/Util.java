// Copyright (C) 2023 Bebo Khouja

package com.mokkachocolata.util;

import com.mokkachocolata.exception.JarNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URISyntaxException;

public class Util {
    /**
	 * Gets the downloaded JAR location in a {@code String}.
	 *
	 * @return The JAR's downloaded file location in a {@code String}.
     * @throws JarNotFoundException
     *         If the JAR is not found.
     * @since 1.1
	 */
    public static @NotNull String getJarLocation() throws URISyntaxException, JarNotFoundException {
        if (new File(Util.class.getProtectionDomain().getCodeSource().getLocation().toURI()).isDirectory()) {
            // Check if the Java program is running under IntelliJ IDEA.
            if (runningFromIntelliJ()) {
                return new File(Util.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            } else {
                throw new JarNotFoundException("Jar is not a .jar");
            }
        } else {
            return new File(Util.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
        }
    }
    /**
     * Gets the downloaded JAR location.
     *
     * @return The JAR's downloaded file location.
     * @throws JarNotFoundException
     *         If the JAR is not found.
     * @since 1.4.0
     */
    public static File getJar() throws URISyntaxException, JarNotFoundException {
        if (new File(Util.class.getProtectionDomain().getCodeSource().getLocation().toURI()).isDirectory()) {
            // Check if the Java program is running under IntelliJ IDEA.
            if (runningFromIntelliJ()) {
                return new File(Util.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            } else {
                throw new JarNotFoundException("Jar is not a .jar");
            }
        } else {
            return new File(Util.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        }
    }

    /**
     * Checks if the Java program is running under IntelliJ IDEA, then returns if it is running under IntelliJ IDEA.
     * @return {@code true} if the current Java program is running under IntelliJ IDEA, {@code false} if not.
     * @since 1.4.0
     */
    public static boolean runningFromIntelliJ()
    {
        String classPath = System.getProperty("java.class.path");
        return classPath.contains("idea_rt.jar");
    }
}
