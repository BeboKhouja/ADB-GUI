// Copyright (C) 2023 Bebo Khouja

package com.mokkachocolata.util;

import com.mokkachocolata.exception.JarNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.jar.JarFile;

/**
 * The {@code Util} class holds the main utility's.
 * @since 1.1
 * @author Bebo Khouja
 */
public class Util {
    /**
	 * Gets the downloaded JAR location in a {@code String}.
	 *
	 * @return The JAR's downloaded file location in a {@code String}.
     * @throws JarNotFoundException
     *         If the JAR is not found.
     * @since 1.1
     * @author Bebo Khouja
	 */
    public @NotNull String getJarLocation() throws URISyntaxException, JarNotFoundException {
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
     * @author Bebo Khouja
     */
    public File getJar() throws URISyntaxException, JarNotFoundException {
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
     * @author Bebo Khouja
     */
    public boolean runningFromIntelliJ()
    {
        String classPath = System.getProperty("java.class.path");
        return classPath.contains("idea_rt.jar");
    }
    /**
     * Gets the main class of the {@code JarFile}.
     * @return {@code String} containing the main class, {@code null} if there is none.
     * @since 1.4.0
     * @author Bebo Khouja
     */
    public @Nullable String getMainClass(JarFile jar) throws IOException {
        if (jar.getManifest().getMainAttributes().containsKey("Main-Class")) {
            return jar.getManifest().getMainAttributes().getValue("Main-Class");
        } else {
            return null;
        }
    }

    /**
     * Trims the {@code File}'s name to the extension.
     * @return {@code String} containing the extension starting with a dot.
     * @since 1.4.0
     * @author Bebo Khouja
     */
    public String getFileExtension(File file) {
        String extension = "";

        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }

        return extension;

    }
}
