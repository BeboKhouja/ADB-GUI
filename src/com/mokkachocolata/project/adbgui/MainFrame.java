// Copyright (C) 2023 Bebo Khouja

package com.mokkachocolata.project.adbgui;

import com.mokkachocolata.util.Link;
import io.sentry.Sentry;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import com.mokkachocolata.util.ExecuteCommand;
import org.apache.log4j.*;

public class MainFrame extends JFrame implements ActionListener, MenuListener, KeyListener {
    private final Logger logger = Logger.getLogger(MainFrame.class);
    private Link link = new Link();

    //Vars
    JTabbedPane tabbedPane = new JTabbedPane();
    JMenuBar menubar1;
    JMenu filemenu1,advancedmenu1,helpmenu1;
    JMenuItem exitmenu1,aboutmenu1,executecommandmenu2,discordmenu3,githubmenu3;
    JButton button1,button2,button3,button4;
    JLabel text1;
    ExecuteCommand CommandBar = new ExecuteCommand();
    Container container = getContentPane();
    // ImageIcon logo = new ImageIcon(".//res//appicon.png")
        JList<Object> list = new JList<>();
        DefaultListModel<Object> model = new DefaultListModel<>();
        JSplitPane splitPane = new JSplitPane();
        JPanel wrapper = new JPanel(); // comes with flowlayout by default
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        JPanel labelPanel = new JPanel(new GridLayout(1, 1));
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel(new GridLayout(2, 1));
        JPanel panel3 = new JPanel();
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
        list.setModel(model);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        tabbedPane.add("Actions",panel2);
        tabbedPane.add("Devices",panel3);
        panel.add(tabbedPane);
        panel.add(buttonPanel);
        panel.add(new JLabel());
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
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(button4);
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
         Sentry.init(options -> {
            options.setDsn("https://237eedd6ad4d4eb0bb09781c6097d4ba@o1374227.ingest.sentry.io/6681459");
            // To set a uniform sample rate
            options.setTracesSampleRate(1.0);
            // OR if you prefer, determine traces sample rate based on the sampling context
            options.setTracesSampler(
                context -> {
                  return null;
                });
          }); // to be removed when you fork it
    }
    

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setThreshold(Level.ALL);
           
        if (arg0.getSource() == button1) {
        logger.info("Execute command \"adb reboot\"");
         new Thread(()->{try {
            Runtime.getRuntime().exec("adb reboot");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }}).start();
        
      }
      if (arg0.getSource() == button2) {
          logger.info("Execute command \"adb reboot recovery\"");
        new Thread(()->{try {
            Runtime.getRuntime().exec("adb reboot recovery");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }}).start(); 
}
    if (arg0.getSource() == button3) {
        logger.info("Execute command \"adb reboot sideload\"");
         new Thread(()->{try {
            Runtime.getRuntime().exec("adb reboot sideload");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }}).start();
        
    }
    if (arg0.getSource() == button4) {
        logger.info("Execute command \"adb kill server\"");
         new Thread(()->{try {
            Runtime.getRuntime().exec("adb kill-server");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }}).start();

    }
    if (arg0.getSource() == aboutmenu1) {
        JOptionPane.showMessageDialog(panel, "ADB GUI\nVersion " + App.version);
     }
    if (arg0.getSource() == exitmenu1) {
        System.exit(0);
     }

    if (arg0.getSource() == executecommandmenu2) {
        CommandBar.setVisible(true);
    }
    if (arg0.getSource() == discordmenu3) {
        link.OpenLink("https://discord.gg/y6AcUNTKxX");
}

    if (arg0.getSource() == githubmenu3) {
        link.OpenLink("https://github.com/BeboKhouja/ADB-GUI");
    }
    }

    
    /**
     */
    public void menuSelected(MenuEvent me) {
    
    }

    
    /**
     */
    public void menuDeselected(MenuEvent me) {
        
    }

    
    /**
     */
    public void menuCanceled(MenuEvent me) {
        
    }
        
    
    
    /**
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

        public void uncaughtException(Thread thread, Throwable exception) {
    
            Sentry.captureException(exception);
            System.out.println("Reported exception thrown!");
            exception.printStackTrace();    
            JOptionPane.showMessageDialog(panel, "An error has occurred! Please report this problem at GitHub or my Discord! \n\n" + exception.getMessage());
            System.exit(0);
    
        }
    }
}