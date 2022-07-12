package com.mokkachocolata.util;
import java.io.File;
import java.net.URISyntaxException;

import com.mokkachocolata.exception.JarNotFoundException;

public class Util {
    /**
	 * Gets the downloaded JAR location.
	 *
	 * @return The JAR's downloaded file location.
     * @throws JarNotFoundException
     *         If the JAR is not found.
     * @since 1.1
	 */
    public static String getJarLocation() throws URISyntaxException, JarNotFoundException {
        if(new File(Util.class.getProtectionDomain().getCodeSource().getLocation().toURI()).isDirectory())  {
            throw new JarNotFoundException("Jar is not found");
        } else {
            return new File(Util.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
        }
    }

}
