package com.mokkachocolata.project.adbgui;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting ADB GUI");
        MainFrame myFrame = new MainFrame();
        System.out.println("Successfully started.");
            System.out.println("Can use adb");
            myFrame.init();
        if (Terminal.isOpenedInConsole(args) == true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Opened in terminal");
            System.out.println("Command:");
            String command = scanner.nextLine();
            Runtime.getRuntime().exec(command);
            System.out.println("Execute command "+command);
        } else {
          System.out.println("Not opened in terminal");
        }
        
    }
}
