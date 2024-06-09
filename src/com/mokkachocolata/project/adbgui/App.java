// Copyright (C) 2024 Bebo Khouja

package com.mokkachocolata.project.adbgui;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;


import com.mokkachocolata.exception.JarNotFoundException;
import com.mokkachocolata.util.*;


import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import org.apache.log4j.*;
import org.json.JSONArray;

import de.jcm.discordgamesdk.activity.*;

import javax.swing.*;

/**
 * The {@code App} class is an essential class which is responsible for starting the application.
 * It houses the main function that is executed first. <br>
 * <strong>Note: </strong>This class should not be initialized.
 * @since 1.0
 * @author Bebo Khouja
 */
public class App {
    public static final String version = "1.5.0";
    private static final Logger logger = Logger.getLogger(App.class);
    private static final AddonLoader addonLoader = new AddonLoader();
    public static final Event onCommandExecute = new Event();


    public static void main(String[] args) throws IOException, URISyntaxException, JarNotFoundException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, UnsupportedLookAndFeelException, InstantiationException {
        // I run first!
        File preferences = new File(new File(new Util().getJarLocation()).getParentFile().getPath() + "\\preferences.json");
        if (!preferences.exists()) {
            //noinspection ResultOfMethodCallIgnored
            preferences.createNewFile();
        }
        JSONArray preferencesJson = new JSONArray(Files.readAllLines(preferences.toPath()));
        System.out.println(Files.readAllLines(preferences.toPath()));

        ArrayList<LoadedAddon> classes = (ArrayList<LoadedAddon>) addonLoader.LoadAddons().clone();
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setThreshold(Level.ALL);
        logger.info("ADB GUI\nVersion ".concat(version));
        for(LoadedAddon clazz : classes) {
            clazz.initializeClassOnAppStartup();
            System.out.println("Current mods: ");
                System.out.println();

        }
        MainFrame myFrame = new MainFrame();
        myFrame.init();
        
    }
}
