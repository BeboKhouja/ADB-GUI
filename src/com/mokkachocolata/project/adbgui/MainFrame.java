package com.mokkachocolata.project.adbgui;
import java.awt.Desktop;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MainFrame extends JFrame implements ActionListener, MenuListener, KeyListener {
    //Vars
    JMenuBar menubar1;
    JMenu filemenu1,advancedmenu1,helpmenu1;
    JMenuItem exitmenu1,aboutmenu1,executecommandmenu2,discordmenu3,githubmenu3;
    JTextField edittext1;
    JButton button1,button2,button3,button4;
    JLabel text1;
    Container container = getContentPane();
    // ImageIcon logo = new ImageIcon(".//res//appicon.png")
        JPanel wrapper = new JPanel(); // comes with flowlayout by default
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        JPanel labelPanel = new JPanel(new GridLayout(1, 1));
        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
    public void init() {
        //Local vars
        
        // Log4j config
        //Window config
        setTitle("ADB GUI");
        setIconImage(Toolkit.getDefaultToolkit().
          getImage(MainFrame.class.getResource("/res/appicon.png")));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(600, 500));
        new JFrame();
        //Main
        wrapper.setPreferredSize(new Dimension(250, 100));
        panel.setLayout(boxLayout);
        panel.setMaximumSize(new Dimension(100, 100));
        menubar1 = new JMenuBar();
        filemenu1 = new JMenu("File");
        advancedmenu1 = new JMenu("Advanced");
        helpmenu1 = new JMenu("Help");
        exitmenu1 = new JMenuItem("Exit");
        aboutmenu1 = new JMenuItem("About ADB GUI");
        executecommandmenu2 = new JMenuItem("Execute command");
        discordmenu3 = new JMenuItem("Discord Server");
        githubmenu3 = new JMenuItem("GitHub Repository");
        button1 = new JButton("Reboot device"); 
        button2 = new JButton("Reboot to recovery");
        button3 = new JButton("Reboot to sideload");
        button4 = new JButton("Reconnect device");
        text1 = new JLabel("Please make sure USB Debugging is enabled. Otherwise it wont work.");
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        
        panel.add(buttonPanel);
        panel.add(labelPanel);
        wrapper.add(panel);
        add(wrapper);
        this.setJMenuBar(menubar1);
        menubar1.add(filemenu1);
        menubar1.add(advancedmenu1);
        menubar1.add(helpmenu1);
        advancedmenu1.setMnemonic(KeyEvent.VK_A);
        filemenu1.setMnemonic(KeyEvent.VK_F);
        aboutmenu1.setMnemonic(KeyEvent.VK_A);
        exitmenu1.setMnemonic(KeyEvent.VK_E);
        executecommandmenu2.setMnemonic(KeyEvent.VK_E);
        helpmenu1.setMnemonic(KeyEvent.VK_H);
        discordmenu3.setMnemonic(KeyEvent.VK_D);
        githubmenu3.setMnemonic(KeyEvent.VK_G);
        filemenu1.add(aboutmenu1);
        filemenu1.add(exitmenu1);
        advancedmenu1.add(executecommandmenu2);
        helpmenu1.add(discordmenu3);
        helpmenu1.add(githubmenu3);
        labelPanel.add(text1);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        button1.setToolTipText("This option reboots the device normally. Usage: 'adb reboot'");
        button2.setToolTipText("This option reboots the device into recovery. Usage: 'adb reboot recovery'");
        button3.setToolTipText("This option reboots the device into recovery then starts sideload mode. Usage: 'adb reboot sideload'");
        button4.setToolTipText("This option kills the ADB server. Usage: 'adb kill-server'");
        this.pack();
        setSize(500, 350);
        setVisible(true);
         //Event add
         aboutmenu1.addActionListener(this);
         exitmenu1.addActionListener(this);
         executecommandmenu2.addActionListener(this);
         discordmenu3.addActionListener(this);
         githubmenu3.addActionListener(this);
         button1.addActionListener(this);
         button2.addActionListener(this);
         button3.addActionListener(this);
         button4.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
           
        if (arg0.getSource() == button1) {
        System.out.println("Execute command 'adb reboot'");
         new Thread(()->{try {
            Runtime.getRuntime().exec("adb reboot");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }}).start();
        
      }
      if (arg0.getSource() == button2) {
        System.out.println("Execute command 'adb reboot recovery'");
        new Thread(()->{try {
            Runtime.getRuntime().exec("adb reboot recovery");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }}).start(); 
}
    if (arg0.getSource() == button3) {
        System.out.println("Execute command 'adb reboot sideload'");
         new Thread(()->{try {
            Runtime.getRuntime().exec("adb reboot sideload");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }}).start();
        
    }
    if (arg0.getSource() == button4) {
        System.out.println("Execute command 'adb kill-server'");
         new Thread(()->{try {
            Runtime.getRuntime().exec("adb kill-server");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }}).start();

    }
    if (arg0.getSource() == aboutmenu1) {
        JOptionPane.showMessageDialog(panel, "ADB GUI\nVersion ".concat(App.version));
     }
    if (arg0.getSource() == exitmenu1) {
        System.exit(0);
     }
    if (arg0.getSource() == executecommandmenu2) {
        String command = JOptionPane.showInputDialog(panel, "Enter command:", "Enter command", JOptionPane.INFORMATION_MESSAGE);
        try {
            if (command != null) {
                System.out.println("Execute command \"".concat("adb ").concat(command).concat("\""));
                Runtime.getRuntime().exec(command.startsWith("adb") + command);
            }
        } catch (IOException e) {
    }
}
    if (arg0.getSource() == discordmenu3) {
    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
        try {
            Desktop.getDesktop().browse(new URI("https://discord.gg/y6AcUNTKxX"));
        } catch (IOException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

    if (arg0.getSource() == githubmenu3) {
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

    public void menuSelected(MenuEvent me) {
    
    }

    public void menuDeselected(MenuEvent me) {
        
    }

    public void menuCanceled(MenuEvent me) {
        
    }
        
    
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

        public void uncaughtException(Thread thread, Throwable exception) {
    
            System.out.println("Reported exception thrown!");
            exception.printStackTrace();    
            JOptionPane.showMessageDialog(panel, "An error has occured! Please report this problem at GitHub or my Discord!");
            System.exit(0);
    
        }
    }
}