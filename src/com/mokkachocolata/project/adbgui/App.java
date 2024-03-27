// Copyright (C) 2023 Bebo Khouja

package com.mokkachocolata.project.adbgui;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mokkachocolata.exception.JarNotFoundException;
import com.mokkachocolata.util.*;


import org.apache.log4j.*;

import javax.swing.*;

/**
 * The {@code App} class is an essential class which is responsible for starting the application.
 * It houses the main function that is executed first. <br>
 * <strong>Note: </strong>This class should not be initialized.
 * @since 1.0
 * @author Bebo Khouja
 */
public class App {
    public static final String version = "1.4.1";
    private static final Logger logger = Logger.getLogger(App.class);
    private static final Link link = new Link();
    private static final AddonLoader addonLoader = new AddonLoader();
    public static final Event onCommandExecute = new Event();


    public static void main(String[] args) throws IOException, URISyntaxException, JarNotFoundException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, UnsupportedLookAndFeelException, InstantiationException {
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
        if (Terminal.isOpenedInConsole(args)) {
            try (Scanner scanner = new Scanner(System.in)) {
                while(true) {
                    String command = scanner.nextLine();
                    onCommandExecute.fireEvent();
                    new Thread(()->{try {
                        Terminal.GetOutput(Runtime.getRuntime().exec("adb " + command));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }}).start();
                    logger.info("Execute command \"adb "+command+"\"");
                    if (command.equals("version")) {
                        logger.info("ADB GUI version " + version);
                    }
                    if (command.equals("help")) {
                        logger.info("Commands:\n1. version\n2. jarlocation\n3. discord\n4. github");
                    }
                    if (command.equals("github")) {
                        link.OpenLink("https://github.com/BeboKhouja/ADB-GUI");
                    }
                    if (command.equals("discord")) {
                        link.OpenLink("https://discord.gg/y6AcUNTKxX");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        
    }
}
