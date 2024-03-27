package com.mokkachocolata.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.jar.JarFile;

/**
 * The LoadedAddon class is a class that contains functions to execute an addon's function. <br>
 * <strong>Note: </strong> This class should only be initialized by the {@code AddonLoader} class.
 * @since 1.5.0
 * @author Bebo Khouja
 */
public class LoadedAddon {
    private final Class currentClass;
    private final URLClassLoader child;
    private File currentAddon;
    private final boolean fileJar;
    private String readFile(InputStream stream) throws IOException
    {
        byte[] encoded = stream.readAllBytes();
        return new String(encoded, Charset.defaultCharset());
    }
    public LoadedAddon(Class clazz, URLClassLoader loader, File addon, boolean isFileJar) throws IOException {
        this.currentClass = clazz;
        this.child = loader;
        this.currentAddon = addon;
        this.fileJar = isFileJar;
    }
    private final JarFile classToJarFile = new JarFile(currentAddon);
    private final InputStream metadata = classToJarFile.getInputStream(classToJarFile.getEntry("metadata.txt"));
    private final String metadataToString = readFile(metadata);
    public void initializeClassOnAppStartup() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = currentClass.getDeclaredMethod("main");
        method.setAccessible(true);
        Object result = method.invoke(child);
    }
    public void initializeClassOnGuiStartup() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = currentClass.getDeclaredMethod("onGuiStartup");
        method.setAccessible(true);
        Object result = method.invoke(child);
    }
    public File getAddonFile() {
        return currentAddon;
    }
    public boolean getFileJar() {
        return fileJar;
    }
    public ArrayList<String> getFullAddonMetadata() {
        if (getFileJar()) {
            ArrayList<String> lines = new ArrayList<>();
            Scanner scanner = new Scanner(metadataToString);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
                // process the line
            }
            scanner.close();
            return lines;
        } else {
            return null;
        }
    }
    public String getAddonMetadata(int entry) {
        if (getFileJar()) {
            String[] listWithStringType;
            ArrayList<String> lines = new ArrayList<>();
            Scanner scanner = new Scanner(metadataToString);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
                // process the line
            }
            scanner.close();
            listWithStringType = (String[]) lines.toArray();
            return listWithStringType[entry];
        } else {
            return null;
        }
    }
    public String getAddonName() {
        return getAddonMetadata(1);
    }
}
