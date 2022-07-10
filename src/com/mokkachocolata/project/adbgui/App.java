package com.mokkachocolata.project.adbgui;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

import com.mokkachocolata.util.Terminal;

public class App {
    public static String version = "1.3";
    public static void main(String[] args) throws IOException {
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
                }
            }
            
        }
        
    }
}
