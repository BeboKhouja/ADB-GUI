// Copyright (C) 2023 Bebo Khouja

package com.mokkachocolata.project.adbgui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.Scanner;

import com.mokkachocolata.exception.JarNotFoundException;
import com.mokkachocolata.util.AddonLoader;
import com.mokkachocolata.util.Link;
import com.mokkachocolata.util.Terminal;
import org.apache.log4j.*;

public class App {
    public static String version = "1.4.0";
    static final Logger logger = Logger.getLogger(App.class);
    private static Link link = new Link();

    public static void main(String[] args) throws IOException, URISyntaxException, JarNotFoundException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setThreshold(Level.ALL);
        logger.info("ADB GUI\nVersion ".concat(version));
        new AddonLoader().LoadAddonsOnInit();
        MainFrame myFrame = new MainFrame();
        myFrame.init();
        if (Terminal.isOpenedInConsole(args) == true) {
            try (Scanner scanner = new Scanner(System.in)) {
                while(true) {
                    String command = scanner.nextLine();
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
            }
            
        }
        
    }
}
