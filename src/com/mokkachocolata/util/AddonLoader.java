package com.mokkachocolata.util;

import com.mokkachocolata.exception.JarNotFoundException;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;

public class AddonLoader {
    public void LoadAddonsOnInit() throws JarNotFoundException, URISyntaxException, IOException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        File mods = new File(new File(Util.getJarLocation()).getParentFile().getPath() + "\\mods");
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
                    if (getFileExtension(files).equals(".class")) {
                        classToLoad = Class.forName(files.getName().replaceFirst("[.][^.]+$", ""), true, child);
                    } else if ((getFileExtension(files).equals(".jar"))) {
                        classToLoad = Class.forName(getMainClass(new JarFile(files)), true, child);
                    } else {
                        throw new ClassNotFoundException();
                    }
                    Method method = classToLoad.getDeclaredMethod("main");
                    method.setAccessible(true);
                    Object result = method.invoke(child);
                }
            }
        }
    }
    private static @Nullable String getMainClass(JarFile jar) throws IOException {
        if (jar.getManifest().getMainAttributes().containsKey("Main-Class")) {
            return jar.getManifest().getMainAttributes().getValue("Main-Class");
        } else {
            return null;
    }
    }
    private String getFileExtension(File file) {
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
