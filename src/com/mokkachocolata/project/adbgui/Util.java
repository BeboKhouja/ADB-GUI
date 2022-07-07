package com.mokkachocolata.project.adbgui;
import java.io.File;
import java.net.URISyntaxException;

public class Util {
    /**
	 * Gets the downloaded JAR location.
	 *
	 * @return The JAR's downloaded file location.
     * @throws URISyntaxException
     *         If the JAR is not found.
     * @since 1.1
	 */
    public static String getJarLocation() throws URISyntaxException {
     return new File(Util.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
    }
}
