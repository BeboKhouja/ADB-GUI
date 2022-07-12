package com.mokkachocolata.project.adbgui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import com.mokkachocolata.exception.JarNotFoundException;
import com.mokkachocolata.util.Terminal;
import com.mokkachocolata.util.Util;

public class App {
    public static String version = "1.3.1";
    public static void main(String[] args) throws IOException, URISyntaxException, JarNotFoundException {
        System.out.println("ADB GUI\nVersion ".concat(version));
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
                    System.out.println("Execute command \"adb "+command+"\"");
                    if (command.equals("version")) {
                        System.out.println("ADB GUI version " + version);
                    }
                    if (command.equals("jarlocation")) {
                        System.out.println(Util.getJarLocation());
                    }
                    if (command.equals("help")) {
                        System.out.println("Handy commands:\n1. version\n2. jarlocation");
                    }
                    if (command.equals("github")) {
                        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                            try {
                                Desktop.getDesktop().browse(new URI("https://github.com/BeboKhouja/ADB-GUI"));
                            } catch (IOException | URISyntaxException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                    if (command.equals("discord")) {
                        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                            try {
                                Desktop.getDesktop().browse(new URI("https://github.com/BeboKhouja/ADB-GUI"));
                            } catch (IOException | URISyntaxException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            
        }
        
    }
}
