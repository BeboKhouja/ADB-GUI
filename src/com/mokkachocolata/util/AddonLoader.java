package com.mokkachocolata.util;

import com.mokkachocolata.exception.JarNotFoundException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.JarFile;

/**
 * The {@code AddonLoader} class is responsible for loading the addons.
 * @since 1.4.0
 * @author Bebo Khouja
 */
public class AddonLoader {
    private final Util util = new Util();

    /**
     * Loads the addons from the {@code mods} folder, then returns a {@code ArrayList} containing the {@code LoadedAddon}'s.
     * @return {@code LoadedAddon} containing functions.
     * @since 1.5.0
     * @author Bebo Khouja
     */
    public ArrayList LoadAddons() throws JarNotFoundException, URISyntaxException, IOException, ClassNotFoundException {
        File mods = new File(new File(util.getJarLocation()).getParentFile().getPath() + "\\mods");
        ArrayList<LoadedAddon> loadedAddons = new ArrayList<LoadedAddon>();
        if (mods.exists()) {
            URLClassLoader child = new URLClassLoader(
                    new URL[] {mods.toURI().toURL()}
            );
            File[] modsList = mods.listFiles();
            System.out.println("Current mods: ");
            for(File current: modsList) {
                System.out.println(current.getPath());
            }
            if (modsList != null) {
                for(File files: modsList) {
                    Class classToLoad;
                    if (util.getFileExtension(files).equals(".class")) {
                        classToLoad = Class.forName(files.getName().replaceFirst("[.][^.]+$", ""), true, child);
                    } else if ((util.getFileExtension(files).equals(".jar"))) {
                        classToLoad = Class.forName(util.getMainClass(new JarFile(files)), true, child);
                    } else {
                        throw new ClassNotFoundException();
                    }
                    loadedAddons.add(new LoadedAddon(classToLoad, child, files, util.getFileExtension(files).equals(".jar")));
                }
            }
        }
        return loadedAddons;
    }
    public File getModsFolder() throws JarNotFoundException, URISyntaxException {
        return new File(new File(util.getJarLocation()).getParentFile().getPath() + "\\mods");
    }
}
